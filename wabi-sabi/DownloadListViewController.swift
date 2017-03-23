//
//  DownloadListViewController.swift
//  wabi-sabi
//
//  Created by Hutchison, Gabriel on 3/9/17.
//  Copyright (c) 2017 Hutchison, Gabriel. All rights reserved.
//

import UIKit

class DownloadListViewController: UITableViewController {
    
    let titleTag = 1, timeTag = 2, descTag = 3
    // a list of tuples: .0 = title of data, .1 = description of data
    var tableData: Array<(String, String, String, String)> = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        refreshControl?.addTarget(self, action: #selector(DownloadListViewController.handleRefresh(_:)), for: UIControlEvents.valueChanged)
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
        if let dirs : [String] = NSSearchPathForDirectoriesInDomains(FileManager.SearchPathDirectory.libraryDirectory, FileManager.SearchPathDomainMask.allDomainsMask, true) as? [String] {
            let dir = dirs[0] // library directory
            let path = dir + "/" + cacheFileName()
            
            var textData: String?
            do {
                textData = try String(contentsOf: URL(string: path)!, encoding: String.Encoding.utf8)
            } catch {/* error handling here */}
            
            if textData == nil {
                NSLog("Cache miss; downloading data")
            } else {
                self.populateTable(parseJSON(try! Data(contentsOf: URL(fileURLWithPath: path))))
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
    
    func populateTable(_ data: NSDictionary) {
        let datum = (data[dataKey()] as! NSArray) as Array
        tableData = []
        for elem in datum {
            let typedElem: NSDictionary = elem as! NSDictionary
            let title = elem["title"] as! String
            let desc = elem["desc"] as! String
            let time = (hasKey(typedElem, key: "time")) ? elem["time"] as! String : ""
            let extendedDesc = (hasKey(typedElem, key: "ext_desc")) ? elem["ext_desc"] as! String : ""
            tableData.append(title, desc, time, extendedDesc)
        }
        tableView.reloadData()
        refreshControl?.endRefreshing()
    }
    
    func hasKey(_ dict: NSDictionary, key: String) -> Bool {
        let allKeys: NSArray = dict.allKeys as NSArray
        return allKeys.contains(key)
    }
    
    func handleRefresh(_ sender:AnyObject) {
        NSLog("Refreshing content")
        downloadData()
        refreshControl?.beginRefreshing()
    }
    
    func downloadData() {
        URLSession.shared.dataTask(with: URL(string: dataURL())!, completionHandler: { (data, response, error) -> Void in
            if error != nil {
                NSLog("Error downloading data")
                return
            }
            DispatchQueue.main.async(execute: { () -> Void in
                // display data
                let list = self.parseJSON(data!)
                self.populateTable(list)
                
                // cache data for future reference
                if let dirs : [String] = NSSearchPathForDirectoriesInDomains(FileManager.SearchPathDirectory.libraryDirectory, FileManager.SearchPathDomainMask.allDomainsMask, true) as? [String] {
                    let dir = dirs[0] // library directory
                    let path = dir + "/" + self.cacheFileName()
                    
                    let data2: Data = data! as Data
                    try? data2.write(to: URL(fileURLWithPath: path), options: [])
                    
                    if ((try? data2.write(to: URL(fileURLWithPath: path), options: [])) != nil) == false {
                        NSLog("Could not cache downloaded data")
                    }
                }
            })
        }).resume();
    }
    
    func getJSON(_ urlToRequest: URL) -> Data {
        return (try! Data(contentsOf: urlToRequest))
    }
    
    func parseJSON(_ inputData: Data) -> NSDictionary {
        var boardsDictionary: NSDictionary?
        do {
            boardsDictionary = try JSONSerialization.jsonObject(with: inputData, options: JSONSerialization.ReadingOptions.mutableContainers) as? NSDictionary
        } catch {}
        return boardsDictionary!
    }
    
    // implement methods from tableviewdatasource
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1;
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tableData.count;
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: cellReuseID(), for: indexPath) as UITableViewCell;
        let title = cell.contentView.viewWithTag(titleTag) as? UILabel
        let time = cell.contentView.viewWithTag(timeTag) as? UILabel
        let desc = cell.contentView.viewWithTag(descTag) as? UIVerticalAlignLabel
        title?.text = tableData[indexPath.row].0
        desc?.text = tableData[indexPath.row].1
        time?.text = tableData[indexPath.row].2
        return cell;
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        refreshControl?.endRefreshing()
    }
    
    func cacheFileName() -> String {
        preconditionFailure("This method must be overriden")
    }
    
    func dataURL() -> String {
        preconditionFailure("This method must be overriden")
    }
    
    func dataKey() -> String {
        preconditionFailure("This method must be overriden")
    }
    
    func cellReuseID() -> String {
        preconditionFailure("This method must be overriden")
    }
}
