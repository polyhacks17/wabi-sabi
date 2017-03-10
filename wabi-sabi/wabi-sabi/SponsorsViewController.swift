//
//  SponsorsViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/9/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class SponsorsViewController: UITableViewController {

    let imageTag = 1, labelTag = 2, urlTag = 3
    let sponsorDir = "SponsorImages"
    // a list of tuples: .0 = cell name, .1 = segue to activate upon clicking the cell.
    let sponsors = [
        ("Florida Poly SGA", "fpu_sga_upscaled.png", "https://floridapolytechnic.org/get-involved/student-government-association/"),
        ("Cole Engineering Services", "cesi.png", "https://www.coleengineering.com/"),
        ("Catapult", "catapult_logo_padded.png", "https://catapultlakeland.com/"),
        ("Twilio", "twilio_logo_padded.png", "https://twilio.com/"),
        ("MLH", "mlh.png", "https://mlh.io/"),
        ("Wolfram", "wolfram_logo_padded.png", "https://wolfram.com/"),
        ("GitHub", "github_logo_padded.png", "https://www.github.com/"),
        ("Scott & Kim Johnson", "", ""),
    ];
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        title = "Sponsors"
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // implement methods from tableviewdatasource
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1;
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return sponsors.count;
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier("SponsorCells", forIndexPath: indexPath) as UITableViewCell;
        var image = cell.contentView.viewWithTag(imageTag) as? UIImageView
        var label = cell.contentView.viewWithTag(labelTag) as? UILabel
        var url = cell.contentView.viewWithTag(urlTag) as? UILabel
        
        // use the provided image. Else, hide the image view
        var imageSrc = sponsors[indexPath.row].1
        if (imageSrc != "") {
            image!.image = UIImage(named: sponsors[indexPath.row].1)
            image!.hidden = false
        } else {
            image!.hidden = true
        }
        label!.text = sponsors[indexPath.row].0
        url!.text = sponsors[indexPath.row].2
        return cell;
    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        tableView.deselectRowAtIndexPath(indexPath, animated: true)
        var cell = tableView.cellForRowAtIndexPath(indexPath)
        var link = cell?.contentView.viewWithTag(urlTag) as? UILabel
        if (link == nil || link == "") {
            return
        }
        // go to the link
        let url = NSURL(string: link!.text!)!
        if (UIApplication.sharedApplication().canOpenURL(url)) {
            UIApplication.sharedApplication().openURL(url)
        }
    }
//    // Failed attempt to only show label if there is no provided image
//    let defaultRowHeight = 273, defaultImageHeight = 157
//    override func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
//        if (sponsors[indexPath.row].1 == "") {
//            return CGFloat(defaultRowHeight - defaultImageHeight)
//        } else {
//            return CGFloat(defaultRowHeight)
//        }
//    }

}
