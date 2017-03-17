//
//  DevTeamViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/9/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class DevTeamViewController: UITableViewController {

    // a list of tuples: .0 = cell name, .1 = segue to activate upon clicking the cell.
    var members = [
        ("Greg Willard", [("Github", "r3pwn", "https://www.github.com/r3pwn"), ("Twitter", "r3pwn", "https://www.twitter.com/r3pwn")]),
        ("Caleb Long", [("Github", "cerras0981", "https://www.github.com/cerras0981")]),
        ("Gabriel Hutchison", [("Github", "PrivacyPolicy", "https://www.github.com/PrivacyPolicy")])
    ];
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        title = "Dev Team"
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // implement methods from tableviewdatasource
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return members.count
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return members[section].1.count;
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier("DevTeamCells", forIndexPath: indexPath) as UITableViewCell;
        var (site, username, link) = members[indexPath.section].1[indexPath.row]
        cell.textLabel?.text = site + " (\(username))"
        cell.detailTextLabel?.text = link
        cell.textLabel?.textColor = self.navigationController?.navigationBar.tintColor
        
        // accessibility
        let name = members[indexPath.section]
        cell.accessibilityHint = "\(name)'s \(site) account is \(username)."
        
        return cell;
    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        var cell = tableView.cellForRowAtIndexPath(indexPath);
        var link = cell?.detailTextLabel?.text;
        if (link == nil) {
            return
        }
        // call the number
        let url = NSURL(string: link!)!
        if (UIApplication.sharedApplication().canOpenURL(url)) {
            UIApplication.sharedApplication().openURL(url)
        }
        tableView.deselectRowAtIndexPath(indexPath, animated: true)
    }
    
    override func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return members[section].0
    }

}
