//
//  AnnouncementsViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/9/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class AnnouncementsViewController: UITableViewController {
    
    let titleTag = 1, timeTag = 2, descTag = 3
    // a list of tuples: .0 = title of announcement, .1 = description of announcement
    var tableData: Array<(String, String)> = []
    let cacheAnouncementsDir = "cache_anouncements.json"
    let dataURL = "https://raw.githubusercontent.com/r3pwn/PolyHacks2017/master/JSON/prod.json"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        refreshControl?.addTarget(self, action: "handleRefresh:", forControlEvents: UIControlEvents.ValueChanged)
        refreshControl?.beginRefreshing()
        
        // load json in a new thread
        loadData()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func loadData() {
        // before downloading, show the local, cached data
        if let dirs : [String] = NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory.LibraryDirectory, NSSearchPathDomainMask.AllDomainsMask, true) as? [String] {
            let dir = dirs[0] // library directory
            let path = dir.stringByAppendingPathComponent(cacheAnouncementsDir)
            
            let textData = String(contentsOfFile: path, encoding: NSUTF8StringEncoding, error: nil)?
            
            if textData == nil {
                NSLog("Cache miss; downloading data")
            } else {
                self.populateTable(parseJSON(NSData(contentsOfFile: path)!))
            }
        }
        
        // download the file, replace the current content when finished
        downloadData()
    }
    
//    func alert(message: AnyObject) {
//        let alert = UIAlertController(title: "ALERT", message: "\(message)", preferredStyle: UIAlertControllerStyle.Alert);
//        alert.addAction(UIAlertAction(title: "KK Chickapay", style: UIAlertActionStyle.Default, handler: nil));
//        self.presentViewController(alert, animated: true, completion: nil);
//    }
    
    func populateTable(data: NSDictionary) {
        let announcements = (data["announcements"] as NSArray) as Array
        tableData = []
        for elem in announcements {
            let title = elem["title"] as String
            let desc = elem["desc"] as String
            tableData.append(title, desc)
        }
        tableView.reloadData()
        refreshControl?.endRefreshing()
    }
    
    func handleRefresh(sender:AnyObject) {
        NSLog("Refreshing content")
        downloadData()
        refreshControl?.beginRefreshing()
    }
    
    func downloadData() {
        NSURLSession.sharedSession().dataTaskWithURL(NSURL(string: dataURL)!, completionHandler: { (data, response, error) -> Void in
            if error != nil {
                print(error)
                NSLog("Error downloading data")
                return
            }
            dispatch_async(dispatch_get_main_queue(), { () -> Void in
                // display data
                var list = self.parseJSON(data)
                self.populateTable(list)
                
                // cache data for future reference
                if let dirs : [String] = NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory.LibraryDirectory, NSSearchPathDomainMask.AllDomainsMask, true) as? [String] {
                    let dir = dirs[0] // library directory
                    let path = dir.stringByAppendingPathComponent(self.cacheAnouncementsDir)
                    
                    let data2: NSData = data as NSData
                    data2.writeToFile(path, atomically: false)
                    
                    if data2.writeToFile(path, atomically: false) == false {
                        NSLog("Could not cache downloaded data")
                    }
                }
            })
        }).resume();
    }
    
    func getJSON(urlToRequest: NSURL) -> NSData {
        return NSData(contentsOfURL: urlToRequest)!
    }
    
    func parseJSON(inputData: NSData) -> NSDictionary {
        var error: NSError?
        var boardsDictionary: NSDictionary = NSJSONSerialization.JSONObjectWithData(inputData, options: NSJSONReadingOptions.MutableContainers, error: &error) as NSDictionary
        return boardsDictionary
    }
    
    // implement methods from tableviewdatasource
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1;
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tableData.count;
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier("AnnouncementsCell", forIndexPath: indexPath) as UITableViewCell;
        var title = cell.contentView.viewWithTag(titleTag) as? UILabel
        var time = cell.contentView.viewWithTag(timeTag) as? UILabel
        var desc = cell.contentView.viewWithTag(descTag) as? UIVerticalAlignLabel
        title?.text = tableData[indexPath.row].0
        desc?.text = tableData[indexPath.row].1
        time?.text = ""
        return cell;
    }
    
    override func viewDidDisappear(animated: Bool) {
        refreshControl?.endRefreshing()
    }
}
