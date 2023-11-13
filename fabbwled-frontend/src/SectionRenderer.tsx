import React from "react";

export type SectionElementStyle = "NONE" | "SECTION" | "ITEM" | "ABILITY" | "TICKBOX";

export type Container = {
  children: SectionElement[];
  type: "CONTAINER";
  style: SectionElementStyle;
};

export type Simple = {
  text: string;
  type: "SIMPLE";
  style: SectionElementStyle;
};

export type Clickable = {
  clickId: number;
  text: string;
  type: "CLICKABLE";
  child: SectionElement;
};

export type SectionElement = Simple | Clickable | Container;

type OnClickCallBack = (clickId: Clickable["clickId"]) => void;

export function convertToElement(
  element: SectionElement,
  onClick: OnClickCallBack,
): React.ReactElement {
  switch (element.type) {
    case "CLICKABLE":
      return (
        <a onClick={() => onClick(element.clickId)}>
          {convertToElement(element.child, onClick)}
        </a>
      );
    case "SIMPLE":
      return <p style={convertStyle(element.style)}>{element.text}</p>;
    case "CONTAINER":
      return (
        <div style={convertStyle(element.style)}>
          {element.children.map((el) => convertToElement(el, onClick))}
        </div>
      );
    default:
      return <></>;
  }
}

export function convertStyle(style: SectionElementStyle): React.CSSProperties {
  switch (style) {
    case "NONE":
      return {};
    case "ITEM":
      return { fontWeight: "bold" };
    case "SECTION":
      return { fontWeight: "bold" };
    case "ABILITY":
      return { textTransform: "uppercase" };
    default:
      return {};
  }
}
