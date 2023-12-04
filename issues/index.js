import fs from "fs";
import { Octokit } from "octokit";

const file = fs.readFileSync("sections.json");
const sections = JSON.parse(file);

const implemented = JSON.parse(fs.readFileSync("existing.json"));

if (!process.env.GH_TOKEN) {
  console.error("You need to set the GH_TOKEN env var");
  process.exit(1);
}

const octokit = new Octokit({
  auth: process.env.GH_TOKEN,
});

const fabbwled = { owner: "keykey7", repo: "fabbwled" };

async function getAllIssues(cache) {
  if (cache) {
    return JSON.parse(fs.readFileSync("issues.json"));
  }
  const issues = [];
  let result;
  let page = 0;
  do {
    console.log(`Doing request, already has ${issues.length} issues`);
    result = await octokit.rest.issues.listForRepo({
      ...fabbwled,
      per_page: 100,
      page,
    });
    page += 1;
    issues.push(...result.data.filter((i) => !i.pull_request));
  } while (result.data.length > 0);
  fs.writeFileSync("issues.json", JSON.stringify(issues));
  return issues;
}

const issues = await getAllIssues(false);

// close duplicate issues
for (const issue of issues) {
  const existing = issues.find(
    (i) => i.title === issue.title && i.number !== issue.number
  );
  if (existing) {
    console.log("duplicate: ", issue.title, issue.number);
    continue;
    await octokit.rest.issues.createComment({
      ...fabbwled,
      issue_number: issue.number,
      body: `[AUTOMATED] I accidentally created this issue as a duplicate of #${existing.number}`,
    });
    console.log("sent comment, closing")
    await octokit.rest.issues.update({
      ...fabbwled,
      issue_number: issue.number,
      state: "closed",
      state_reason: "not_planned",
    });
  }
}
console.log(issues.length);

// Detect issues that have the wrong state.
for (const issue of issues) {
  // We currently only get open issues
  const isImplemented = implemented.find(
    (section) => `${section}`.length >= 3 && issue.title.includes(` ${section}`)
  );
  if (!isImplemented && issue.state === "closed") {
    console.log(
      `Should be open: section ${isImplemented}, issue ${issue.number}`
    );
  }
  if (isImplemented && issue.state === "open") {
    console.log(
      `should be closed: section ${isImplemented}, issue ${issue.number}`
    );
  }
}

// Create missing issues
for (const section of sections) {
  const [id, text] = section;
  const existsOnGh = issues.some((issue) => issue.title.includes(`${id}`));
  if (existsOnGh) {
    continue;
  }
  console.log("creating issue for section", id);
  const fences = "```\n";
  const body =
    "**THIS ISSUE WAS CREATED AUTOMATICALLY**\n" +
    fences +
    text +
    "\n" +
    fences;
  if (true) {
    await octokit.rest.issues.create({
      ...fabbwled,
      title: `Section ${id} Book 1`,
      body,
      labels: ["section", "region-backend"],
    });
  }
}
