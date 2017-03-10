//
//  MapViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/9/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class MapViewController: UIViewController {

    @IBOutlet weak var map: UIImageView!

    @IBAction func tapMap(sender: UITapGestureRecognizer) {
        var touchPoint = sender.locationInView(map)
        var imageRatio = map.image!.size.height / map.image!.size.width
        var displayHeight = map.bounds.width * imageRatio
        var displayTop = map.bounds.height / 2 - 0.5 * displayHeight
        var left = touchPoint.x / map.bounds.width
        var top = (touchPoint.y - displayTop) / displayHeight
        
        let alert = UIAlertController(title: "Alert", message: "Message: (\(left), \(top)).", preferredStyle: UIAlertControllerStyle.Alert);
        alert.addAction(UIAlertAction(title: "Click", style: UIAlertActionStyle.Default, handler: nil));
        self.presentViewController(alert, animated: true, completion: nil);
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

