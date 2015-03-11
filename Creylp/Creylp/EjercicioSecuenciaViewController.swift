//
//  EjercicioSecuenciaViewController.swift
//  Creylp
//
//  Created by Marco Baeza on 06-03-15.
//  Copyright (c) 2015 Marco Baeza. All rights reserved.
//

import UIKit
import Alamofire

class EjercicioSecuenciaViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        Alamofire.request(.GET, "https://api.instagram.com/v1/tags/malaysia/media/recent?client_id=166f90e2af5d491bb44a84d12c3e0aa1").responseJSON { (request, response, json, error) in
            if json != nil {
                var jsonObj = JSON(json!)
                if let data = jsonObj["data"].arrayValue as [JSON]?{
                    self.datas = data
                    self.collectionView.reloadData()
                }
            }
        }
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
