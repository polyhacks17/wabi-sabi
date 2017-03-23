//
//  InfoViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/9/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class InfoViewController: UITableViewController {
    
    // a list of tuples: .0 = cell name, .1 = segue to activate upon clicking the cell.
    var links = [
        ("Sponsors", "SegueToSponsors"),
        ("Contacts", "SegueToEmergencyContacts"),
        ("Dev Team", "SegueToDevTeam"),
//        ("Licenses", "SegueToLicenses")
    ];
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        title = "Info"
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // implement methods from tableviewdatasource
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1;
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return links.count;
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath) as UITableViewCell;
        cell.textLabel?.text = links[indexPath.row].0;
        return cell;
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let cell = tableView.cellForRow(at: indexPath);
        let text = cell?.textLabel?.text;
        if (text == nil) {
            return;
        }
        var segueID: String?
        for (name, segue) in links {
            if (name == text) {
                segueID = segue
            }
        }
        performSegue( withIdentifier: segueID!, sender: self )
        tableView.deselectRow(at: indexPath, animated: true)
//        let alert = UIAlertController(title: "Alert", message: "Message: \(segueID!).", preferredStyle: UIAlertControllerStyle.Alert);
//        alert.addAction(UIAlertAction(title: "Click", style: UIAlertActionStyle.Default, handler: nil));
//        self.presentViewController(alert, animated: true, completion: nil);
    }
    
//    override func tableView(tableView: UITableView, titleForFooterInSection section: Int) -> String? {
//        return "You are currently using the official PolyHacks iOS app.";
//    }
    
    
}

