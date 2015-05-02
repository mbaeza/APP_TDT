//
//  EjercicioAlumnoViewController.swift
//  Creylp
//
//  Created by Marco Baeza on 09-03-15.
//  Copyright (c) 2015 Marco Baeza. All rights reserved.
//

import UIKit

class EjercicioAlumnoViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var alumnosEjercicios: UITableView!
    
    var idEjercicio:String!
    var idUsuario:String!
    var items: [String] = []

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.alumnosEjercicios.registerClass(UITableViewCell.self, forCellReuseIdentifier: "cell")
        
        
        var post:NSString = "username"
        
        // NSLog("PostData: %@",post);
        
        var url:NSURL = NSURL(string: "http://localhost:8080/APP_TDT_WEB-war/webresources/ServiciosWeb/ObtenerAlumnosEjercicios/"+idEjercicio+"/"+idUsuario)!
        
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
            let res = response as! NSHTTPURLResponse!;
            
            NSLog("Response code: %ld", res.statusCode);
            
            if (res.statusCode >= 200 && res.statusCode < 300)
            {
                var responseData:NSString = NSString(data:urlData!, encoding:NSUTF8StringEncoding)!
                
                NSLog("Response ==> %@", responseData);
                
                var error: NSError?
                
                let jsonData:NSDictionary = NSJSONSerialization.JSONObjectWithData(urlData!, options:NSJSONReadingOptions.MutableContainers , error: &error) as! NSDictionary
                
                
                let success:NSString = jsonData.valueForKey("respuesta")?.valueForKey("codigo")  as! NSString
                
                //[jsonData[@"success"] integerValue];
                
                NSLog("Success: " + (success as String));
                
                if(success == "00")
                {
                    
                    NSLog("Obtener Ejercicios SUCCESS");
                    
                    let ejercicios:NSArray = jsonData.valueForKey("listaAlumnos")  as! NSArray
                    
                    // var ej:NSDictionary = ejercicios[0] as! NSDictionary
                    
                    // var valor:NSString = ej.valueForKey("descripcionEjercicio")? as! NSString
                    // NSLog("Valor de la descripcion: " +  valor);
                    
                    for( var i = 0;i<ejercicios.count;i++ ){
                        var ejercicio:NSDictionary = ejercicios[i] as! NSDictionary
                        var nombreAlumno:NSString = ejercicio.valueForKey("nombre") as! NSString
                        var apellidoPaternoAlumno:NSString = ejercicio.valueForKey("apellidoPaterno") as! NSString
                        var apellidoMaternoAlumno:NSString = ejercicio.valueForKey("apellidoMaterno") as! NSString
                        
                        NSLog("Nombre del Alumno: " + (nombreAlumno as String) + " " + (apellidoPaternoAlumno as String));
                        items.append((nombreAlumno as String) + " " + (apellidoPaternoAlumno as String) + " " + (apellidoMaternoAlumno as String));
                        
                        
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

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.items.count;
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell:UITableViewCell = self.alumnosEjercicios.dequeueReusableCellWithIdentifier("UITableViewCellEA") as! UITableViewCell
        
        cell.textLabel?.text = self.items[indexPath.row]
        
        return cell
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
    }
    
    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let cell = sender as! UITableViewCell
        let indexPath = self.alumnosEjercicios.indexPathForCell(cell)
        
        // load the selected model
        let item = self.items[indexPath!.row]
        
        let viewController = segue.destinationViewController as! SecuenciaEjercicioViewController
        // set the model to be viewed
        viewController.idUsuario = self.idUsuario;
        viewController.idEjercicio = self.idEjercicio
    //******Falta idAlumno
    }
    

    
}
