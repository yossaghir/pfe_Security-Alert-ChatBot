package media;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;
import javax.swing.JOptionPane;
public class ScanAutre {
	public int x;
        private String fichier;
        private String log;
        public boolean a = true;
        int pos = 0;
        String aPos ="";
        int ps;
        int count=0;
        String Admin;
        
        public ScanAutre(){
        }
        public void analyser(boolean x) throws InterruptedException{
            a=x;
        while(a) {
                File f = new File(aPos);
            if(f.exists() && !f.isDirectory()) { 
                //check if position file existes
            try {
            BufferedReader num = new BufferedReader(new FileReader(aPos));
            pos = Integer.parseInt(num.readLine());
            num.close();
        } catch (Exception ex) {
            System.out.println("error");
        }
            }   
           /* File fi = new File(fichier);
            if(fi.length()==0){
                JOptionPane.showMessageDialog(null, "Pas de mots\nAjoutez des mots avant de démarrer!!");
                break;
            }*/
		String ligneLue,ligne; // pour stocker chaque ligne du fichier
		String str="";
		try {
		BufferedReader p = new BufferedReader (new FileReader(fichier));
		while ((ligne = p.readLine())!= null){                                 
                    ps = pos;
                    BufferedReader b = new BufferedReader (new FileReader(log)) ;
                for (int j = 1; j <= ps; j++)
                    b.readLine();
		while ((ligneLue = b.readLine())!= null){
			//String [] tokens = ligneLue.split(" ");
                        //for(int j = 0;j<tokens.length;j++)
			if(ligneLue.toLowerCase().contains(ligne)) {
                                ++count;
                        }
                        ps++;
                        }
		b.close();
                if(count>0){
                    sendMessage(count,ligne);
                    System.out.println(count+" Mots \"" +ligne+ "\" trouvé \n");
                    count = 0 ;
                }
                }		
		} catch(Exception e){
		System.out.println(e.toString());
                exit(0);}
                System.out.println("nombre de dernier ligne analysée en fichier log : "+pos);
		Thread.sleep(1000);
                pos=ps;
                try {
                   BufferedWriter write = new BufferedWriter(new FileWriter(aPos));
                    write.write(pos+"");
                    write.close();
                    } catch (Exception ex) {
                        System.out.println("error");
        }
        }
        }
        public void sendMessage(int count,String str) throws IOException{
                    String c = count+"";
		    String[] cmd = {"python","SqlAlert.py",str,c,Admin};
	            Process proc=Runtime.getRuntime().exec(cmd);
                }
        public void setFichier(String stri){
            this.fichier=stri;
        }
        public void setLog(String stri){
            this.log=stri;
        }
        public void setPosFile(String stri){
            this.aPos=stri;
        }
         public void setAdmin(String str){
            this.Admin=str;
        }
}       