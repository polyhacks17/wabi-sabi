//
//  MapViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/9/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit
import ImageScrollView

class MapViewController: UIViewController {
    
    var navImages: [Int:String]
    var mapImages: [Int:UIImage]
    var FIRST_FLOOR: Int
    var SECOND_FLOOR: Int
    var floor: Int
    var mapTitle: String
    var floorTitles: [Int:String]

    required init(coder aDecoder: NSCoder) {
        FIRST_FLOOR = 0
        SECOND_FLOOR = 1
        floor = FIRST_FLOOR
        navImages = [
            FIRST_FLOOR: "button_up",
            SECOND_FLOOR: "button_down"
        ]
        mapTitle = "Map"
        floorTitles = [
            FIRST_FLOOR: "First Floor",
            SECOND_FLOOR: "Commons"
        ]
        mapImages = [
            FIRST_FLOOR: UIImage(named: "ist_map_bottomfloor")!,
            SECOND_FLOOR: UIImage(named: "ist_map_eventfloor")!
        ]
        super.init(coder: aDecoder)
    }

    @IBOutlet weak var scrollView: ImageScrollView!
    
    @IBOutlet weak var floorNavButton: UIBarButtonItem!
    @IBAction func floorNavButtonTap(sender: UIBarButtonItem) {
        floor = (floor == FIRST_FLOOR) ? SECOND_FLOOR : FIRST_FLOOR
        floorNavButton.image = UIImage(named: navImages[floor]!)
        // fade in and out to make it look nicer
        UIView.animateWithDuration(1/8, animations: {
            self.scrollView.alpha = 0
            }, completion: { (complete: Bool) in
                self.scrollView.displayImage(self.mapImages[self.floor]!)
                UIView.animateWithDuration(1/8, animations: {
                    self.scrollView.alpha = 1
                    })
        })
        changeNavTitle(self.floor)
    }
    
//    func alert(message: AnyObject) {
//        let alert = UIAlertController(title: "ALERT", message: "\(message)", preferredStyle: UIAlertControllerStyle.Alert);
//        alert.addAction(UIAlertAction(title: "KK Chickapay", style: UIAlertActionStyle.Default, handler: nil));
//        self.presentViewController(alert, animated: true, completion: nil);
//    }
    
    func changeNavTitle(floor: Int) {
        self.navigationItem.title = self.mapTitle + " (" + self.floorTitles[floor]! + ")"
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // hack way to make the view defaulted to zoom = 1
        floor = SECOND_FLOOR
        floorNavButtonTap(UIBarButtonItem())
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

