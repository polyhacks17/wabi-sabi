//
//  ScheduleInfoViewController.swift
//  PolyHacks
//
//  Created by Hutchison, Gabriel on 3/15/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class ScheduleInfoViewController: UIViewController {

    @IBOutlet weak var textView: UITextView!
    var text = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        textView.text = text;
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
