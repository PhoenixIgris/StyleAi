//
//  PanoramaViewController.swift
//  iosApp
//
//  Created by Cubit Sachita on 16/07/2025.
//  Copyright © 2025 orgName. All rights reserved.
//


import UIKit
import SceneKit

class PanoramaViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        let scnView = SCNView(frame: view.bounds)
        view.addSubview(scnView)

        // Create the scene
        let scene = SCNScene()
        scnView.scene = scene

        // Create the camera
        let cameraNode = SCNNode()
        cameraNode.camera = SCNCamera()
        scene.rootNode.addChildNode(cameraNode)

        // Position the camera at the center of the sphere
        cameraNode.position = SCNVector3Zero

        // Create the sphere
        let sphere = SCNSphere(radius: 10)
        sphere.firstMaterial?.isDoubleSided = true
        sphere.firstMaterial?.diffuse.contents = UIImage(named: "panorama.jpg") // Add your 360 image here

        // Invert the sphere so the camera sees the inside
        let sphereNode = SCNNode(geometry: sphere)
        scene.rootNode.addChildNode(sphereNode)

        // Enable user interaction
        scnView.allowsCameraControl = true
    }
}
