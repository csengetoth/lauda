package teszt;


	import java.io.*;

	class LZWBinFa
	{

	     public LZWBinFa ()
	    {
		gyoker=new Csomopont('/');
		fa=gyoker;    
	}


	    void add (char b)
	    {
	        if (b == '0')
	        {
	            if (fa.nullasGyermek ()==null)	
	            {
	                Csomopont uj = new Csomopont ('0');
	                fa.ujNullasGyermek (uj);
	                fa = gyoker;
	            }
	            else			
	            {
	                fa = fa.nullasGyermek ();
	            }
	        }
	        else
	        {
	            if (fa.egyesGyermek ()==null)
	            {
	                Csomopont uj = new Csomopont ('1');
	                fa.ujEgyesGyermek (uj);
	                fa = gyoker;
	            }
	            else
	            {
	                fa = fa.egyesGyermek ();
	            }
	        }
	    }

	public int getMelyseg ()
	{
	    melyseg = maxMelyseg = 0;
	    rmelyseg (gyoker);
	    return maxMelyseg - 1;
	}

	public double getAtlag ()
	{
	    melyseg = atlagosszeg = atlagdb = 0;
	    ratlag (gyoker);
	    atlag = ((double) atlagosszeg) / atlagdb;
	    return atlag;
	}

	public double getSzoras ()
	{
	    atlag = getAtlag ();
	    szorasosszeg = 0.0;
	    melyseg = atlagdb = 0;

	    rszoras (gyoker);

	    if (atlagdb - 1 > 0)
	        szoras = Math.sqrt (szorasosszeg / (atlagdb - 1));
	    else
	        szoras = Math.sqrt (szorasosszeg);

	    return szoras;
	}

	public void rmelyseg (Csomopont elem)
	{
	    if (elem != null)
	    {
	        ++melyseg;
	        if (melyseg > maxMelyseg)
	            maxMelyseg = melyseg;
	        rmelyseg (elem.egyesGyermek ());
	        rmelyseg (elem.nullasGyermek ());
	        --melyseg;
	    }
	}

	public void ratlag (Csomopont  elem)
	{
	    if (elem != null)
	    {
	        ++melyseg;
	        ratlag (elem.egyesGyermek ());
	        ratlag (elem.nullasGyermek ());
	        --melyseg;
	        if (elem.egyesGyermek () == null && elem.nullasGyermek () == null)
	        {
	            ++atlagdb;
	            atlagosszeg += melyseg;
	        }
	    }
	}

	public void rszoras (Csomopont  elem)
	{
	    if (elem != null)
	    {
	        ++melyseg;
	        rszoras (elem.egyesGyermek ());
	        rszoras (elem.nullasGyermek ());
	        --melyseg;
	        if (elem.egyesGyermek () == null && elem.nullasGyermek () == null)
	        {
	            ++atlagdb;
	            szorasosszeg += ((melyseg - atlag) * (melyseg - atlag));
	        }
	    }
	}

	public  void kiir (PrintWriter os)
	    {
	        melyseg = 0;
	        kiir (gyoker, os);
	    }

	    class Csomopont
	    {
	        Csomopont (char b)
	        {
		if(b=='0' || b=='1')
	 		betu=b;
		else
			betu='/';
	        };

	        Csomopont nullasGyermek () 
	        {
	            return balNulla;
	        }
	        
	        Csomopont egyesGyermek () 
	        {
	            return jobbEgy;
	        }
	        
	        void ujNullasGyermek (Csomopont  gy)
	        {
	            balNulla = gy;
	        }
	        
	        void ujEgyesGyermek (Csomopont  gy)
	        {
	            jobbEgy = gy;
	        }
	        
	        char getBetu () 
	        {
	            return betu;
	        }

	     private   char betu;
	     private   Csomopont balNulla;
	     private   Csomopont jobbEgy;

	    };

	    Csomopont fa;
	    private int melyseg, atlagosszeg, atlagdb;
	    private double szorasosszeg;
	   
	    public void kiir (Csomopont  elem, PrintWriter os)
	    {
	        if (elem != null)
	        {
	            ++melyseg;
	            kiir (elem.egyesGyermek (), os);
	            for (int i = 0; i < melyseg; ++i)
	                os.print ("---");
	            os.print( elem.getBetu () + "(" + (melyseg-1) + ")\n" );
	            kiir (elem.nullasGyermek (), os);
	            --melyseg;
	        }
	    }

	   protected Csomopont gyoker;
	   protected int maxMelyseg;
	   protected double atlag, szoras;
	public static void
	usage ()
	{
	    System.out.println( "Usage: lzwtree in_file -o out_file");
	}

	public static void  main (String[] args) throws FileNotFoundException, IOException

	{
	 if (args.length < 3)
	    {
	        usage ();
	        return ;
	    }


	String inFile = args[0];

	 if(!args[1].equals("-o"))
	        {
	        	 System.out.println(args[1]);
	        	 usage ();
	             return;
	        }

	        BufferedReader input = new BufferedReader(new FileReader(inFile));

	        
	        if (input == null)
	        {
	            System.out.println("Nem létezik");
	            usage ();
	            return;
	        }

	 PrintWriter output = new PrintWriter(args[2]);

	        int b;		
	        LZWBinFa binfa = new LZWBinFa();

		 while ((b = input.read ()) != -1)
	        {
	             if ((char)b == '1' || (char)b == '0')
	                	binfa.add((char)b);

	        }

	        binfa.kiir(output);		

	        output.print("depth " + binfa.getMelyseg () + "\n");
	        output.print("mean " + binfa.getAtlag () + "\n");
	        output.print("var " + binfa.getSzoras () + "\n");

	        output.close ();
	        input.close ();

	        return;
	}

	};

