package styleai.core.ui.components

import platform.UIKit.UIView


object LottieViewProvider{
    var provideLottieView : (String)-> UIView = { UIView() }
}
