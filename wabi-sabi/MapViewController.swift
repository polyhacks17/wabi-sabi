//
//  MapViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/9/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class MapViewController: UIViewController {
    
    var navImages: [Int:String]
    var mapImages: [Int:String]
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
        mapImages = [
            FIRST_FLOOR: "ist_map_bottomfloor.png",
            SECOND_FLOOR: "ist_map_eventfloor.png"
        ]
        mapTitle = "Map"
        floorTitles = [
            FIRST_FLOOR: "First Floor",
            SECOND_FLOOR: "Commons"
        ]
        super.init(coder: aDecoder)
    }

    @IBOutlet weak var map: UIImageView!

//    @IBAction func tapMap(sender: UITapGestureRecognizer) {
//        var touchPoint = sender.locationInView(map)
//        var imageRatio = map.image!.size.height / map.image!.size.width
//        var displayHeight = map.bounds.width * imageRatio
//        var displayTop = map.bounds.height / 2 - 0.5 * displayHeight
//        var left = touchPoint.x / map.bounds.width
//        var top = (touchPoint.y - displayTop) / displayHeight
//        
//        let alert = UIAlertController(title: "Alert", message: "Message: (\(left), \(top)).", preferredStyle: UIAlertControllerStyle.Alert);
//        alert.addAction(UIAlertAction(title: "Click", style: UIAlertActionStyle.Default, handler: nil));
//        self.presentViewController(alert, animated: true, completion: nil);
//    }
    
    @IBOutlet weak var floorNavButton: UIBarButtonItem!
    @IBAction func floorNavButtonTap(sender: UIBarButtonItem) {
        floor = (floor == FIRST_FLOOR) ? SECOND_FLOOR : FIRST_FLOOR
        // change image to show updated floor
        map.image = UIImage(named: mapImages[floor]!)
        floorNavButton.image = UIImage(named: navImages[floor]!)
        // fade in and out to make it look nicer
        UIView.animateWithDuration(0.25, animations: {
            self.map.alpha = 0;
            self.map.image = UIImage(named: self.mapImages[self.floor]!);
            self.map.alpha = 1;
        })
        changeNavTitle(self.floor)
    }
    
    func alert(message: AnyObject) {
        let alert = UIAlertController(title: "ALERT", message: "\(message)", preferredStyle: UIAlertControllerStyle.Alert);
        alert.addAction(UIAlertAction(title: "KK Chickapay", style: UIAlertActionStyle.Default, handler: nil));
        self.presentViewController(alert, animated: true, completion: nil);
    }
    
    func changeNavTitle(floor: Int) {
        self.navigationItem.title = self.mapTitle + " (" + self.floorTitles[floor]! + ")"
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        changeNavTitle(self.floor)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

