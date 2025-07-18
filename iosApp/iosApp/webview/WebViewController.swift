//
//  WebViewController.swift
//  PhotoSphereIosImplementation
//
//  Created by Cubit Sachita on 17/07/2025.
//


import UIKit
import WebKit

class WebViewController: UIViewController {

    var webView: WKWebView!

    override func viewDidLoad() {
        super.viewDidLoad()

        // Create and configure WKWebView
        webView = WKWebView(frame: .zero)
        webView.translatesAutoresizingMaskIntoConstraints = false
        webView.configuration.preferences.setValue(true, forKey: "allowFileAccessFromFileURLs")
        view.addSubview(webView)

        // Add constraints
        NSLayoutConstraint.activate([
            webView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            webView.bottomAnchor.constraint(equalTo: view.bottomAnchor),
            webView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            webView.trailingAnchor.constraint(equalTo: view.trailingAnchor)
        ])

        // Load a URL
        loadPanoramaHTML()
    }
    private func loadPanoramaHTML() {
        if let htmlPath = Bundle.main.path(forResource: "panorama", ofType: "html") {
            let url = URL(fileURLWithPath: htmlPath)
            webView.loadFileURL(url, allowingReadAccessTo: url.deletingLastPathComponent())
        }

      }
}


