//
//  UIVerticalAlignLabel.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/13/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

open class UIVerticalAlignLabel: UILabel {
    
    enum VerticalAlignment : Int {
        case verticalAlignmentTop = 0
        case verticalAlignmentMiddle = 1
        case verticalAlignmentBottom = 2
    }
    
    var verticalAlignment : VerticalAlignment = .verticalAlignmentTop {
        didSet {
            setNeedsDisplay()
        }
    }
    
    public required init(coder aDecoder: NSCoder){
        super.init(coder: aDecoder)!
    }
    
    override open func textRect(forBounds bounds: CGRect, limitedToNumberOfLines: Int) -> CGRect {
        let rect = super.textRect(forBounds: bounds, limitedToNumberOfLines: limitedToNumberOfLines)
        
        switch(verticalAlignment) {
        case .verticalAlignmentTop:
            return CGRect(x: bounds.origin.x, y: bounds.origin.y, width: rect.size.width, height: rect.size.height)
        case .verticalAlignmentMiddle:
            return CGRect(x: bounds.origin.x, y: bounds.origin.y + (bounds.size.height - rect.size.height) / 2, width: rect.size.width, height: rect.size.height)
        case .verticalAlignmentBottom:
            return CGRect(x: bounds.origin.x, y: bounds.origin.y + (bounds.size.height - rect.size.height), width: rect.size.width, height: rect.size.height)
        default:
            return bounds
        }
    }
    
    override open func drawText(in rect: CGRect) {
        let r = self.textRect(forBounds: rect, limitedToNumberOfLines: self.numberOfLines)
        super.drawText(in: r)
    }
}
