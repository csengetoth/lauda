privileged aspect swap {
	
	void around(char b, LZWBinFa f): 
	call(public void add(char)) && target (f) && args (b) {

	if (b == '1')
        {
            if (f.fa.nullasGyermek ()==null)	
            {
                LZWBinFa.Csomopont uj = f.new Csomopont ('0');
                f.fa.ujNullasGyermek (uj);
                f.fa = f.gyoker;
            }
            else			
            {
                f.fa = f.fa.nullasGyermek ();
            }
        }
        else
        {
            if (f.fa.egyesGyermek ()==null)
            {
                LZWBinFa.Csomopont uj = f.new Csomopont ('1');
                f.fa.ujEgyesGyermek (uj);
                f.fa = f.gyoker;
            }
            else
            {
                f.fa = f.fa.egyesGyermek ();
            }
        }
    }
}
