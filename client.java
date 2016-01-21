/**
 * 
 */
package Factom;
import java.util.Scanner;

/**
 * @author matt whittington
 * @version 0.1
 * 
 * This is a command line interface for dealing with the apiCalls class. 
 *
 */
public class client {
	
	/**
	 *  for usage run man
	 */
	public static void main(String[] args) {

		String test=""; 
/*
		byte[] TempArray=new byte[0];
		byte[] bhello=utils.sha256String("HELLO");
		byte[] bworld=utils.sha256String("WORLD");
		byte[] b2016=utils.sha256String("FACTOM 2016!");		
		String shello=utils.bytesToHex(bhello);
		String sworld=utils.bytesToHex(bworld);
		String s2016=utils.bytesToHex(b2016);

								
		System.out.println(shello);
		System.out.println(sworld);
		System.out.println(s2016);
		
		TempArray=utils.
		String appendstrings =utils.hexToBytes(shello + sworld + s2016);
System.exit(1);*/
		if (args.length == 0) {
			 test= man("ALL");			
		} else {
			if (args[0].equals("addfee")) {
				try {
					test=apiCalls.AddFee(args[1],args[2]);
				} catch (Exception e) {
					test=man(args[0]);
				}
			}
			if (args[0].equals("addinput")) {
				try {
					long factoshi=0;
					
					//factoshi are whole numbers.
				    factoshi=Long.parseLong(String.valueOf(Float.parseFloat(args[3]) * 100000000) );

					test=apiCalls.AddInput(args[1],args[2],factoshi);
				} catch (Exception e) {
					test=man(args[0]);
				}				
				
			}
			else if (args[0].equals("addoutput")) {
				try {
					test=apiCalls.AddOutput(args[1],args[2],Integer.parseInt(args[3]));
				} catch (Exception e) {
					test=man(args[0]);
				}					
				
			}
			else if (args[0].equals("addecoutput")) {
				try {
					test=apiCalls.AddECOutput(args[1],args[2],Integer.parseInt(args[3]));
				} catch (Exception e) {
					test=man(args[0]);
				}					
				
			}
			else if (args[0].equals("balance")) {
				try {
					if (args[1].equals("fct")){
						test=apiCalls.GetFactoidBalance(args[2]);					
					} else if (args[1].equals("ec")) {
						test=apiCalls.GetEntryCreditBalance(args[2]);							
					} else {
						test=man(args[0]);					
					}

				} catch (Exception e) {
					test=man(args[0]);
				}					
				
			}
			else if (args[0].equals("balances")) {
				try {
					test=apiCalls.GetAddresses();
					test=formatGetAddress(test);
				} catch (Exception e) {
					test=man(args[0]);
				}	
			}
			else if (args[0].equals("buyentrycredits")) {
				try {
					test=apiCalls.BuyEntryCreditsFullTransaction(args[1],args[2],Float.parseFloat(args[3]));
				} catch (Exception e) {
					test=man(args[0]);
				}					
				
			}
			else if (args[0].equals("deletetransaction")) {
				try {
					test=apiCalls.DeleteTransaction(args[1]);
				} catch (Exception e) {
					test=man(args[0]);
				}				
				
			}
			else if (args[0].equals("get")) {
				try {
					
					if (args[1].equals("head")) {
						test=apiCalls.GetDBlockHead();
					}
					else if (args[1].equals("height")) {
						test=apiCalls.GetDBlockHeight();
					}
					else if (args[1].equals("dblock")) {
						if (args[2].equals("keymr")){
							System.out.println("Please enter a merkler root, not 'keymr'");
						} else {
							test=apiCalls.GetDBlock(args[2]);	
							if (test.indexOf("response code: 400") > 0 ) {  //400 is a valid not found response in this case
								test="Directory Block Not Found.";
							}							
						}
	
					}
					else if (args[1].equals("chain")) {
						if (args[2].equals("chainid")){
							System.out.println("Please enter a chain id, not 'chainid'");
						} else {
							test=apiCalls.GetChainHead(args[2]);		
							if (test.indexOf("response code: 400") > 0 ) {  //400 is a valid not found response in this case
								test="Chain Not Found.";
							}
						}
	
					}
					else if (args[1].equals("eblock")) {
						if (args[2].equals("keymr")){
							System.out.println("Please enter a merkler root, not 'keymr'");
						} else {
							test=apiCalls.GetEBlock(args[2]);		
							if (test.indexOf("response code: 400") > 0 ) {  //400 is a valid not found response in this case
								test="Entry Block Not Found.";
							}
						}
	
					}
					else if (args[1].equals("entry")) {
						if (args[2].equals("hash")){
							System.out.println("Please enter an entry hash, not 'hash'");
						} else {
							test=apiCalls.GetEntry(args[2]);
							if (test.indexOf("response code: 400") > 0 ) {  //400 is a valid not found response in this case
								test="Entry Hash Not Found.";
							}	
						}
	
					}
					else if (args[1].equals("firstentry")) {
						if (args[2].equals("chainid")){
							System.out.println("Please enter a chain id, not 'chainid'");
						} else {
							test=apiCalls.GetFirstEntry(args[2]);						
						}
	
					}
				} catch (Exception e) {
					test=man(args[0]);
				}					
			}
			else if (args[0].equals("getaddresses")) {
				try {
					test=apiCalls.GetAddresses();
					test=formatGetAddress(test);
				} catch (Exception e) {
					man(args[0]);
				}				

			}
			else if (args[0].equals("getexchangerate")) {
				try {
					test=apiCalls.GetExchangeRate();
				} catch (Exception e) {
					man(args[0]);
				}					
				
			}
			else if (args[0].equals("getfee")) {

				try {
					if (args.length == 1) {
						test=apiCalls.GetFee(" ");		
					} else {
						test=apiCalls.GetFee(args[1]);					
					}
				} catch (Exception e) {
					man(args[0]);
				}	
			}
			else if (args[0].equals("list")) {
				try {
					test=apiCalls.GetProcessedTransactions(args[1],"");					
				} catch (Exception e){
					man(args[0]);
				}

			}
			else if (args[0].equals("mkchain")) {
				try {
			    Scanner stdin = new Scanner(System.in);
			    String chainBody="";
			    System.out.println("Enter the content of your entry here.  \nEnter ctrl-Z on a blank line to end keyboard capture");
			    while(stdin.hasNext()){
			    	String line = stdin.next();
			        chainBody += " " + line;
			    }
			System.out.println("out of stdin");
				stdin.close();
			    //args[1] is the payment address
				// semantics, but chain body is really 'entry body'
				// chains don't have bodies. Entries do.  This also makes initial entry
				test=apiCalls.ComposeChainCommit(args[1],args,chainBody);
				} catch (Exception e){
					man(args[0]);
				}
			}
			else if (args[0].equals("newaddress") || args[0].equals("generateaddress")) {
				try { // if the correct number of arguments are not sent, call man
					if (args.length == 3) {
					  if (args[1].toLowerCase().equals("fct")){
						  test=apiCalls.GenerateFactoidAddress(args[2]);
					  } else if (args[1].toLowerCase().equals("ec")){
						  test=apiCalls.GenerateEntryCreditAddress(args[2]);
					  }
					} else if (args.length == 4) {
						  if (args[1].toLowerCase().equals("fct")){
							  test=apiCalls.GenerateAddressFromHumanReadablePrivateKey(args[2],args[3]);
						  } else if (args[1].toLowerCase().equals("ec")){
							  test=apiCalls.GenerateEntryCreditAddressFromHumanReadablePrivateKey(args[2],args[3]);
						  }					   
					} else {
						// not enough or too many command line arguments
						man("newaddress");
					}
			          
					} catch (Exception e) {
					  man("newaddress"); 	
					}
			}
			else if (args[0].equals("newtransaction")) {
				try {
					test=apiCalls.NewTransaction(args[1]);
				} catch (Exception e){
					man(args[0]);
				}
			}
			else if (args[0].equals("put")) {
				try {
				    Scanner stdin = new Scanner(System.in);
				    String entryBody="";
				    System.out.println("Enter the content of your entry here.  \nEnter ctrl-Z on a blank line to end keyboard capture");
				    while(stdin.hasNext()){
				    	String line = stdin.next();
				    	entryBody += " " + line;
				    }
				
					stdin.close();
				    //args[1] is the payment address
					test=apiCalls.ComposeEntryCommit(args[1],args,entryBody);
					} catch (Exception e){
						man(args[0]);
				}
			}
			else if (args[0].equals("sign")) {
				try {
					test=apiCalls.SignTransaction(args[1]);
				} catch (Exception e){
					man(args[0]);
				}
			}
			else if (args[0].equals("submit")) {
				try {
					test=apiCalls.SubmitTransaction(args[1]);
				} catch (Exception e){
					man(args[0]);
				}
			}
			else if (args[0].equals("transactions")) {
				try {
						test=apiCalls.GetTransactions();
				} catch (Exception e){
					man(args[0]);
				}
			}
			else if (args[0].equals("sendfactoids")) {
				try {
					test=apiCalls.SendFactoidsFullTransaction(args[1],args[2],Float.parseFloat(args[3]));
				} catch (Exception e){
					man(args[0]);
				}
			} else {
			 man("ALL");				
			}
			
		}
	
		System.out.println(test);
	}
	
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DIRECTIONS FOR USE
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static String man(String cmd) {
System.out.println("java -jar FactomAPI.jar	[options]	[subcommand]");		
if (cmd.equals("ALL") || cmd.equals("get")) { 
System.out.println("  get          ");			
System.out.println("     head                Get the keymr of the last completed directory block");					
System.out.println("     height              Get the currenct directory block height");					
System.out.println("     dblock keymr        Get dblock contents by merkle root");					
System.out.println("     chain chainid       Get ebhead by chainid");					
System.out.println("     eblock keymr        Get eblock by merkle root");					
System.out.println("     firstentry chainid  Get the first entry in a chain");					
System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("mkchain")) {
System.out.println("  mkchain name           Create a new factom chain. read the data for the");			
System.out.println("                         first entry from stdin.  Use the entry credits");			
System.out.println("                         from the specified name.");			
System.out.println("     -e externalid       External Id for the first entry. Can be used");			
System.out.println("                         more than once");			
System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("put")) {
System.out.println("  put name               Read data from stdid and write to Factom.  Use");			
System.out.println("                         the entry credits from the specified entry credit");			
System.out.println("                         address.");			
System.out.println("     -e [externalid]     Specify an external id for the factom entry. -e");			
System.out.println("                         can be used multiple times.");			
System.out.println("     -c [chainid]        Specify the chain that the entry belongs to");			
System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("newaddress") || cmd.equals("generateaddress")) {
System.out.println("  new address or");
System.out.println("  generateaddress        Generate addresses, giving them names.");
System.out.println("     ec name             Generate an Entry Credit address, tied to the name.");
System.out.println("     fct name            Generate a factoid address, tied to the name.");
System.out.println("                         Names must be unique, or you will get a");
System.out.println("                         Duplicate Name or Invalid Name Error.");
System.out.println("     ec name Es...       Import a secret EC key to the wallet");
System.out.println("     fct name Fs...      Import a secret Factoid key to the wallet");
System.out.println(" ");
}
if (cmd.equals("ALL") || cmd.equals("addinput")) {
System.out.println("  addinput               Add an input to a transaction");			
System.out.println("     key name amount     Use the name supplied in newaddress");			
System.out.println("     key address amount  Use an address");			
System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("addecoutput")) {
System.out.println("  addecoutput            Add an ecoutput (Purchase of entry credits) to");			
System.out.println("                         a transaction");			
System.out.println("     key name amount     Use the name supplied in newaddress");			
System.out.println("     key address amount  Use an address");			
System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("outinput")) {
System.out.println("  addoutput              Add an output to a transaction");			
System.out.println("     key name amount     Use the name supplied in newaddress");			
System.out.println("     key address amount  Use an address");			
System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("deletetransaction")) {
System.out.println("  deletetransaction key  Delete the specified transaction in flight.");			
System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("balance")) {
System.out.println("  balance type address    If this is an ec balance, returns the number of.");			
System.out.println("                         entry credits.");			
System.out.println("                         If this is a Factoid balance, returns the");			
System.out.println("                         factoids at that address.");			
System.out.println("                         type should be fct or ec");			
System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("getfee")) {
System.out.println("  getfee key             Get the current fee required for this");			
System.out.println("                         transaction.  If a transaction is specified,");			
System.out.println("                         then getfee returns the fee due for the ");			
System.out.println("                         transaction.  If no transaction is provided,");			
System.out.println("                         then the cost of en Entry Credit is returned.");			
System.out.println("                         (for EC cost, please use getExchangeRate)");			
System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("addfee")) {
System.out.println("  addfee trans address   Adds the needed fee to the given transaction.");			
System.out.println("                         The address specified must be an input to");			
System.out.println("                         the transaction, and it must have a balance");			
System.out.println("                         able to cover the additional fee.  Also, the");			
System.out.println("                         inputs must exactly balance the outputs,");			
System.out.println("                         since the logic to understand what to do ");			
System.out.println("                         otherwise is quite complicated, and prone");			
System.out.println("                         to odd behavior.");			
System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("getExchangeRate")) {
System.out.println("  etexchangerate         Get the current exchange rate for");			
System.out.println("                         factoids to Entry Credits");			

System.out.println(" ");	
}
if (cmd.equals("ALL") || cmd.equals("newtransaction")) {
System.out.println("  newtransaction key     Create a new transaction. The key is used to");			
System.out.println("                         add inputs, outputs, and ecoutputs (to buy");			
System.out.println("                         entry credits).  Once the transaction is built,");			
System.out.println("                         call validate, and if all is good, submit.");			
System.out.println(" ");
}
if (cmd.equals("ALL") || cmd.equals("signtransaction")) {
System.out.println("  sign key               Sign the transaction specified by the key.");			
System.out.println(" ");
}
if (cmd.equals("ALL") || cmd.equals("submittransaction")) {
System.out.println("  submit key             Sign the transaction specified by the key.");			
System.out.println(" ");
}
if (cmd.equals("ALL") || cmd.equals("balances") || cmd.equals("getaddresses")) {
System.out.println("  balances or");			
System.out.println("  getaddresses           Returns the list of addresses known to the");			
System.out.println("                         wallet.  Returns the name that can be used");			
System.out.println("                         tied to each address, as well as the base 58");			
System.out.println("                         address (which is the actual address).  This");			
System.out.println("                         command also returns the balances at each");			
System.out.println("                         address.");			
System.out.println(" ");
}
if (cmd.equals("ALL") || cmd.equals("sendfactoids")) {
System.out.println("  sendfactoids           single call to send factoid.");			
System.out.println("     from to amount      from - address sending factoid.");			
System.out.println("                         to - address receiving factoid.");			
System.out.println("                         amount - in factoids");			
System.out.println(" ");
}
if (cmd.equals("ALL") || cmd.equals("buyentrycredits")) {
System.out.println("  buyentrycredits        single call to buy entry credits.");			
System.out.println("     from to amount      from - address sending factoid.");			
System.out.println("                         to - ec address receiving factoid.");			
System.out.println("                         amount - in factoids");			
System.out.println(" ");
}
if (cmd.equals("ALL") || cmd.equals("list")) {
System.out.println("  list                   List confirmed transactions' details.");			
System.out.println("     [transaction id]    List the confirmed transactions that use the");			
System.out.println("                         transaction id.");			
System.out.println("     [address]           Dumps all factoid transactions that use the");			
System.out.println("                         given address as an input or output.");			
System.out.println("     all                 Dumps all Factoid transactions to date (this wallet).");			
		
System.out.println(" ");
}


return " "; // this is just managing the test println in main.
}  //man


private static String formatGetAddress(String json){
	String resp="";
	try {
	if (json.indexOf("Success\":true}") > 0 ){
	resp=json.replace("{\"Response\":\"","");
	resp=resp.replace("\",\"Success\":true}","");
	resp=resp.replace("\\n", System.lineSeparator() );
	} else {
		return json;
	}
	} catch (Exception e) {
		resp=e.getMessage();
	}
	
	return resp;
}

}
