//
//  SvgHelper.swift
//  iosApp
//
//  Created by Cubit Sachita on 07/07/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import StyleAi

func parseSVGFile(named fileName: String) -> [SVGDataIOS] {
    var result = [SVGDataIOS]()
    let parser = SVGParser()
    if let url = Bundle.main.url(forResource: fileName, withExtension: "svg") {
        parser.parseSVG(from: url)
        result.append(contentsOf: parser.svgDataList)
    }
    return result
}
class SVGParser: NSObject, XMLParserDelegate {
    var svgDataList: [SVGDataIOS] = []

    private var currentGroupName: String?
    private var inSeatGroup = false

    func parseSVG(from url: URL) {
        if let parser = XMLParser(contentsOf: url) {
            parser.delegate = self
            parser.parse()
        }
    }

    func parser(_ parser: XMLParser, didStartElement elementName: String,
                namespaceURI: String?, qualifiedName qName: String?,
                attributes attributeDict: [String : String] = [:]) {

        if elementName == "g", let groupId = attributeDict["id"] {
            if groupId.contains("seat") {
                currentGroupName = groupId
                inSeatGroup = true
            }
        }

        if elementName == "path", inSeatGroup {
            if let d = attributeDict["d"] {
                _ = attributeDict["style"] ?? ""
                let data = SVGDataIOS(name: currentGroupName ?? "unknown", pathData: d)
                svgDataList.append(data)
            }
        }
    }

    func parser(_ parser: XMLParser, didEndElement elementName: String,
                namespaceURI: String?, qualifiedName qName: String?) {
        if elementName == "g" {
            inSeatGroup = false
            currentGroupName = nil
        }
    }


}
