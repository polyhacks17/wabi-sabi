//
//  AnnouncementsViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/13/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class AnnouncementsViewController: DownloadListViewController {
    
    let debugging = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func cacheFileName() -> String {
        return "cache_data.json"
    }
    
    override func dataURL() -> String {
        // Random number to prevent caching
        let rand: String = NSDate().timeIntervalSince1970.description
        if debugging {
            return "https://www.polyhacks.com/eventData/test.json?uncache=" + rand
        } else {
            return "https://raw.githubusercontent.com/polyhacks17/json/master/prod.json?uncache=" + rand
        }
    }
    
    override func dataKey() -> String {
        return "announcements"
    }
    
    override func cellReuseID() -> String {
        return "AnnouncementsCell"
    }
}
