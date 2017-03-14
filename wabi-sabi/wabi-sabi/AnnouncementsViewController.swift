//
//  AnnouncementsViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/13/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class AnnouncementsViewController: DownloadListViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func cacheFileName() -> String {
        return "cache_announcements.json"
    }
    
    override func dataURL() -> String {
        return "https://raw.githubusercontent.com/r3pwn/PolyHacks2017/master/JSON/prod.json"
    }
    
    override func dataKey() -> String {
        return "announcements"
    }
    
    override func cellReuseID() -> String {
        return "AnnouncementsCell"
    }
}
