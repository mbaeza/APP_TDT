//
//  EjerciciosMemorizeViewController.swift
//  Creylp
//
//  Created by Marco Baeza on 05-04-15.
//  Copyright (c) 2015 Marco Baeza. All rights reserved.
//

import UIKit

class EjerciciosMemorizeViewController: UIViewController {

    @IBOutlet weak var tableView: UITableView!
    
    var tipoEjercicio:String!;
    var idUsuario:String!;
    
    var items: [String] = []
    var itemsIdEjercicio: [String] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.registerClass(UITableViewCell.self, forCellReuseIdentifier: "cell")
        
        
        var post:NSString = "username"
        
        // NSLog("PostData: %@",post);
        
        var url:NSURL = NSURL(string: "http://localhost:8080/APP_TDT_WEB-war/webresources/ServiciosWeb/ObtenerEjercicios/"+tipoEjercicio+"/"+idUsuario)!
        
        var postData:NSData = post.dataUsingEncoding(NSASCIIStringEncoding)!
        
        var postLength:NSString = String( postData.length )
        
        var request:NSMutableURLRequest = NSMutableURLRequest(URL: url)
        request.HTTPMethod = "GET"
        //request.HTTPBody = postData
        //request.setValue(postLength, forHTTPHeaderField: "Content-Length")
        request.setValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        
        
        var reponseError: NSError?
        var response: NSURLResponse?
        
        var urlData: NSData? = NSURLConnection.sendSynchronousRequest(request, returningResponse:&response, error:&reponseError)
        
        if ( urlData != nil ) {
            let res = response as NSHTTPURLResponse!;
            
            NSLog("Response code: %ld", res.statusCode);
            
            if (res.statusCode >= 200 && res.statusCode < 300)
            {
                var responseData:NSString = NSString(data:urlData!, encoding:NSUTF8StringEncoding)!
                
                NSLog("Response ==> %@", responseData);
                
                var error: NSError?
                
                let jsonData:NSDictionary = NSJSONSerialization.JSONObjectWithData(urlData!, options:NSJSONReadingOptions.MutableContainers , error: &error) as NSDictionary
                
                
                let success:NSString = jsonData.valueForKey("respuesta")?.valueForKey("codigo")  as NSString
                
                //[jsonData[@"success"] integerValue];
                
                NSLog("Success: " + success);
                
                if(success == "00")
                {
                    
                    NSLog("Obtener Ejercicios SUCCESS");
                    
                    let ejercicios:NSArray = jsonData.valueForKey("listaEjercicios")?  as NSArray
                    NSLog("Obtener Ejercicios SUCCESS");
                    // var ej:NSDictionary = ejercicios[0] as NSDictionary
                    
                    // var valor:NSString = ej.valueForKey("descripcionEjercicio")? as NSString
                    // NSLog("Valor de la descripcion: " +  valor);
                    
                    for( var i = 0;i<ejercicios.count;i++ ){
                        
                        var ejercicio:NSDictionary = ejercicios[i] as NSDictionary
                        var nombreEjercicio:NSString = ejercicio.valueForKey("nombreEjercicio") as NSString
                        var idEjercicio:Int = ejercicio.valueForKey("idEjercicio") as Int
                        
                        NSLog("Nombre del ejercicio: " + nombreEjercicio);
                        items.append(nombreEjercicio);
                        itemsIdEjercicio.append(String(idEjercicio))
                        
                    }
                    // items.append(tipoEjercicio);
                    
                    var prefs:NSUserDefaults = NSUserDefaults.standardUserDefaults()
                    // prefs.setObject(username, forKey: "USERNAME")
                    //prefs.setInteger(1, forKey: "ISLOGGEDIN")
                    // prefs.synchronize()
                    
                    
                } else {
                    var alertView:UIAlertView = UIAlertView()
                    alertView.title = "Error de servicio"
                    alertView.message = "No fue posible obtener los ejercicios"
                    alertView.delegate = self
                    alertView.addButtonWithTitle("OK")
                    alertView.show()
                }
            } else {
                var alertView:UIAlertView = UIAlertView()
                alertView.title = "Error de conexión"
                alertView.message = "Conexión Erronea"
                if let error = reponseError {
                    alertView.message = (error.localizedDescription)
                }
                alertView.delegate = self
                alertView.addButtonWithTitle("OK")
                alertView.show()
            }
        }
        
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.items.count;
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell:UITableViewCell = self.tableView.dequeueReusableCellWithIdentifier("UITableViewCell") as UITableViewCell
        
        cell.textLabel?.text = self.items[indexPath.row]
        
        return cell
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
    }
    
    
    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let cell = sender as UITableViewCell
        let indexPath = self.tableView.indexPathForCell(cell)
        
        // load the selected model
        let item = self.items[indexPath!.row]
        
        let viewController = segue.destinationViewController as EjercicioAlumnoMemorizeViewController
        // set the model to be viewed
        viewController.idUsuario = self.idUsuario;
        viewController.idEjercicio = self.itemsIdEjercicio[indexPath!.row]
    }


}
