public void getIntArrays(int [] a , int[] b, double theta){
    Mat2 m = new Mat2(new Apoint(1,0,0),new Apoint(0,1,0));
    m = m.rotate(theta - Math.PI/2);
    m.xM = Apoint.multByScalar(m.xM,5);
    m.yM = Apoint.multByScalar(m.yM,80);
    Apoint d = Apoint.add(    new Apoint()/*body.getPos()*/ , Apoint.multByScalar(new Apoint(Math.cos(theta),Math.sin(theta),0),5)  );
    Apoint e = Apoint.add(d,m.getXM());
    Apoint f = Apoint.add(d,m.getYM());
    Apoint g = Apoint.add(d,Apoint.add(m.getXM(),m.getYM()));

    

    a[0]=(int)d.getX();
    a[1]=(int)e.getX();
    a[2]=(int)f.getX();
    a[3]=(int)g.getX();

    b[0]=(int)d.getY();
    b[1]=(int)e.getY();
    b[2]=(int)f.getY();
    b[3]=(int)g.getY();
  }