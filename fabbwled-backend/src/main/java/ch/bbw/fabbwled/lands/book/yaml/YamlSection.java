package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;

import java.util.List;

public record YamlSection(SectionId id, List<Action> actions) {
    public void simpleVerify() {
        this.actions.forEach(Action::simpleVerify);
    }

    public void verifyReachability() {
        var reachable = YamlActionReachabilityHelper.verifyActionList(actions);
        if (reachable == YamlReachabilityResult.NORMAL) {
            throw new FabledTechnicalException("Section may not terminate. Every section needs to end with turnTo on every path.");
        }
    }
}
