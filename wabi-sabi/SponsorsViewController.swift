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
        ("Scott & Kim Johnson", "skjohnson.png", ""),
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
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1;
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return sponsors.count;
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "SponsorCells", for: indexPath) as UITableViewCell;
        let image = cell.contentView.viewWithTag(imageTag) as? UIImageView
        let label = cell.contentView.viewWithTag(labelTag) as? UILabel
        let url = cell.contentView.viewWithTag(urlTag) as? UILabel
        
        // use the provided image. Else, hide the image view
        let imageSrc = sponsors[indexPath.row].1
        if (imageSrc != "") {
            image!.image = UIImage(named: sponsors[indexPath.row].1)
            image!.isHidden = false
        } else {
            image!.isHidden = true
        }
        label!.text = sponsors[indexPath.row].0
        url!.text = sponsors[indexPath.row].2
        return cell;
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let cell = tableView.cellForRow(at: indexPath)
        let link = cell?.contentView.viewWithTag(urlTag) as? UILabel
        if (link == nil || link!.text == "") {
            return
        }
        // go to the link
        let url = URL(string: link!.text!)!
        if (UIApplication.shared.canOpenURL(url)) {
            UIApplication.shared.openURL(url)
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
