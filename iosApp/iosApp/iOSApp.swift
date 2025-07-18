import SwiftUI
import StyleAi
import Lottie
import UIKit


@main
struct iOSApp: App {

    init(){
        LottieViewProvider.shared.provideLottieView = { content in

            let view =  LottieAnimationView(
                url: URL(string: content)!,
                closure: {error in print(error as Any)
                })

            view.loopMode = .loop
            view.contentMode = .scaleAspectFit


            DispatchQueue.main.asyncAfter(deadline: .now() + 0.1, execute: {
                view.play()
            })
         
            return view;
        }
        SvgDataListProvider.shared.provideSvgData = { content in
            let result =  parseSVGFile(named: content)
            return result
        }
        PanoramaImageProvider.shared.providePanoramaImage = {
            return WebViewController()
        }
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
