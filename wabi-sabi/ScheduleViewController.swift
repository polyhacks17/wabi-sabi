//
//  ScheduleViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/13/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class ScheduleViewController: DownloadListViewController {
    
    let debugging = false
    let segueScheduleInfoID = "SegueToScheduleInfo"
    
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
        if debugging {
            return "https://www.polyhacks.com/eventData/test.json"
        } else {
            return "https://www.polyhacks.com/eventData/data.json"
        }
    }
    
    override func dataKey() -> String {
        return "schedule"
    }
    
    override func cellReuseID() -> String {
        return "ScheduleCell"
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == segueScheduleInfoID {
            var selectedPath = tableView.indexPathForSelectedRow()!
            
            // get data of currently selected row
            let title = tableData[selectedPath.row].0
            let desc = tableData[selectedPath.row].1
            let time = tableData[selectedPath.row].2
            let ext_desc = tableData[selectedPath.row].3
//
            var scheduleInfo = segue.destinationViewController as ScheduleInfoViewController
            scheduleInfo.title = title
            scheduleInfo.text = "Start time: \(time)\n\n\(desc)\n\n\(ext_desc)"
            
            
            tableView.deselectRowAtIndexPath(selectedPath, animated: true)
        }
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = super.tableView(tableView, cellForRowAtIndexPath: indexPath)
        cell.accessoryType = UITableViewCellAccessoryType.DisclosureIndicator
        return cell
    }
    
    override func tableView(tableView: UITableView, accessoryButtonTappedForRowWithIndexPath indexPath: NSIndexPath) {
        tableView.selectRowAtIndexPath(indexPath, animated: true, scrollPosition: UITableViewScrollPosition.None)
    }
}
