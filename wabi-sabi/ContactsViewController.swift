//
//  ContactsViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/9/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit
import MessageUI

class ContactsViewController: UITableViewController, MFMessageComposeViewControllerDelegate {

    // a list of tuples: .0 = cell name, .1 = segue to activate upon clicking the cell.
    var numbers = [
        ("Campus Police", "(863)-874-8472", "For non-emergencies contact the police department by tapping the number above. If you are near a blue light emergency phone, simply press the red button and you will be contacted by a police officer. For any emergency situations, dial 911."),
        ("PolyHacks Dev Team", "(863)-606-8486", "Texting this number will put you in contact with one of the members of the Dev Team. You can contact us if you wish to speak with a mentor or wish to receive technical help related to the event.")
    ];
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        title = "Contacts"
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // implement methods from tableviewdatasource
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return numbers.count;
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1;
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier("NumberCells", forIndexPath: indexPath) as UITableViewCell;
        cell.textLabel?.text = numbers[indexPath.section].1
        cell.textLabel?.textColor = self.navigationController?.navigationBar.tintColor
        return cell;
    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        var cell = tableView.cellForRowAtIndexPath(indexPath);
        var number = cell?.textLabel?.text;
        if (number == nil) {
            return;
        }
        if indexPath.row == 0 { // campus police #
            // call the number
            let url = NSURL(string: "telprompt://\(number!)")!
            if (UIApplication.sharedApplication().canOpenURL(url)) {
                UIApplication.sharedApplication().openURL(url)
            }
        } else if indexPath.row == 1 { // textable dev team #
            if MFMessageComposeViewController.canSendText() {
                let controller = MFMessageComposeViewController()
                controller.body = "Message From PolyHacks App User"
                controller.recipients = [numbers[indexPath.row].1]
                controller.messageComposeDelegate = self
                self.presentViewController(controller, animated: true, completion: nil)
            }
        }
        tableView.deselectRowAtIndexPath(indexPath, animated: true)
    }
    
    func messageComposeViewController(controller: MFMessageComposeViewController!, didFinishWithResult result: MessageComposeResult) {
        self.dismissViewControllerAnimated(true, completion: nil)
    }
    
    override func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return numbers[section].0
    }
    
    override func tableView(tableView: UITableView, titleForFooterInSection section: Int) -> String? {
        return numbers[section].2
    }

}
