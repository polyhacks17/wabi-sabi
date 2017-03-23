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
        // Random number to prevent caching
        let rand: String = Date().timeIntervalSince1970.description
        if debugging {
            return "https://www.polyhacks.com/eventData/test.json?uncache=" + rand
        } else {
            return "https://raw.githubusercontent.com/polyhacks17/json/master/prod.json?uncache=" + rand
        }
    }
    
    override func dataKey() -> String {
        return "schedule"
    }
    
    override func cellReuseID() -> String {
        return "ScheduleCell"
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == segueScheduleInfoID {
            var selectedPath = tableView.indexPathForSelectedRow!
            
            // get data of currently selected row
            let title = tableData[selectedPath.row].0
            let desc = tableData[selectedPath.row].1
            let time = tableData[selectedPath.row].2
            let ext_desc = tableData[selectedPath.row].3

            let scheduleInfo = segue.destination as! ScheduleInfoViewController
            scheduleInfo.title = title
            scheduleInfo.text = "Start time: \(time)\n\n\(desc)\n\n\(ext_desc)"
            
            
            tableView.deselectRow(at: selectedPath, animated: true)
        }
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = super.tableView(tableView, cellForRowAt: indexPath)
        cell.accessoryType = UITableViewCellAccessoryType.disclosureIndicator
        return cell
    }
    
    override func tableView(_ tableView: UITableView, accessoryButtonTappedForRowWith indexPath: IndexPath) {
        tableView.selectRow(at: indexPath, animated: true, scrollPosition: UITableViewScrollPosition.none)
    }
}
