//
//  TickleGestureRecognizer.swift
//  am_tdt
//
//  Created by Marco Baeza on 23-11-14.
//  Copyright (c) 2014 USACH. All rights reserved.
//

import UIKit

class TickleGestureRecognizer: UIGestureRecognizer {
    // 1
    let requiredTickles = 2
    let distanceForTickleGesture:CGFloat = 25.0
    
    // 2
    enum Direction:Int {
        case DirectionUnknown = 0
        case DirectionLeft
        case DirectionRight
    }
    
    // 3
    var tickleCount:Int = 0
    var curTickleStart:CGPoint = CGPointZero
    var lastDirection:Direction = .DirectionUnknown
    
    
    


}
