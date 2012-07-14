package Graph;
/****************************************/
/* File: Maxflow.java			*/
/* based on Ford-Fulkerson Method	*/
/* Copyright (C) 1997, 1998 K. Ikeda	*/
/****************************************/

import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.URL;

class Node {
	int	x;
	int	y;
	int	delta_plus;	/* _Edge starts from this node */
	int	delta_minus;	/* _Edge terminates at this node */
	int	dist;		/* distance from the start node */
	int	prev;		/* previous node of the shortest path */
	int	p__Edge;
	int	l;
	int	w;
	int	h;
	String	name;
}

class _Edge {
	int	rndd_plus;	/* initial vertex of this _Edge */
	int	rndd_minus;	/* terminal vertex of this _Edge */
	int	delta_plus;	/* _Edge starts from rndd_plus */
	int	delta_minus;	/* _Edge terminates at rndd_minus */
	int	capacity;	/* capacity */
	int	flow;		/* flow */
	int	st;
	String	name;
}

public class Maxflow extends Applet {
	int	n,m;
	int	snode,tnode;	/* start node, terminate node */
	int	step;
	Node	v[] = new Node[100];
	_Edge	e[] = new _Edge[200];

	int findNode(String name) {
		for (int i=0; i<n; i++)
			if (v[i].name.equals(name))
				return i;
		return -1;
	}

	void input_graph(InputStream is) throws IOException {
		int x,y,l;
		String	s;

		StreamTokenizer st = new StreamTokenizer(is);
		st.commentChar('#');
		st.nextToken();	n = (int)st.nval;
		st.nextToken();	m = (int)st.nval;
		st.nextToken();	s = st.sval;
		for (int i = 0; i<n; i++) {
			Node node = new Node();
			st.nextToken();	node.name = st.sval;
			st.nextToken();	node.x = (int)st.nval;
			st.nextToken();	node.y = (int)st.nval;
			v[i] = node;
		}
		for (int i = 0; i<m; i++) {
			_Edge _Edge = new _Edge();
			st.nextToken();	_Edge.name = st.sval;
			switch (st.nextToken()) {
			case StreamTokenizer.TT_NUMBER:
				_Edge.rndd_plus = (int)st.nval;
				break;
			case StreamTokenizer.TT_WORD:
				_Edge.rndd_plus = findNode(st.sval);
				break;
			default:
				break;
			}
			switch (st.nextToken()) {
			case StreamTokenizer.TT_NUMBER:
				_Edge.rndd_minus = (int)st.nval;
				break;
			case StreamTokenizer.TT_WORD:
				_Edge.rndd_minus = findNode(st.sval);
				break;
			default:
				break;
			}
			st.nextToken();	_Edge.capacity = (int)st.nval;
			_Edge.flow = 0;
			e[i] = _Edge;
		}
		step = 1;
	}

	void rdb() {
		int	i,k;

		for (i=0; i<n; i++)
			v[i].delta_plus = v[i].delta_minus = -1;
		for (i=0; i<m; i++)
			e[i].delta_plus = e[i].delta_minus = -1;
		for (i=0; i<m; i++) {
			k = e[i].rndd_plus;
			if (v[k].delta_plus == -1)
				v[k].delta_plus = i;
			else {
				k = v[k].delta_plus;
				while(e[k].delta_plus >= 0)
					k = e[k].delta_plus;
				e[k].delta_plus = i;
			}
			k = e[i].rndd_minus;
			if (v[k].delta_minus == -1)
				v[k].delta_minus = i;
			else {
				k = v[k].delta_minus;
				while(e[k].delta_minus >= 0)
					k = e[k].delta_minus;
				e[k].delta_minus = i;
			}
		}
	}

	void stpath() {		/* find an s-t path */
		int	u[] = new int[100], ni, no;
		int	i,j,d;

		for (i=0; i<n; i++) {
			v[i].prev = v[i].dist = -1;
			v[i].l = 0;
		}
		for (i=0; i<m; i++)
			e[i].st = -1;
		ni = no = 0;
		d = 0;
		u[ni] = snode;
		v[snode].dist = 0;
		j = v[snode].delta_plus;
		i = 0;
		while (j>=0) {
			if (i<e[j].capacity)
				i = e[j].capacity;
			j = e[j].delta_plus;
		}
		v[snode].l = i;

		for (; no<=ni; no++) {
			d = v[u[no]].dist;
			for (j=v[u[no]].delta_plus; j>=0; j=e[j].delta_plus) {
				if (e[j].capacity-e[j].flow == 0)
					continue;
				i = e[j].rndd_minus;
				if (v[i].dist<0) {
					v[i].dist = d+1;
					v[i].prev = u[no];
					v[i].p__Edge = j;
					v[i].l = Math.min(v[u[no]].l,
						e[j].capacity-e[j].flow);
					e[j].st++;
					u[++ni] = i;
				}
			}
			for (j=v[u[no]].delta_minus; j>=0; j=e[j].delta_minus) {
				if (e[j].flow == 0)
					continue;
				i = e[j].rndd_plus;
				if (v[i].dist<0) {
					v[i].dist = d+1;
					v[i].prev = u[no];
					v[i].p__Edge = j;
					v[i].l = Math.min(v[u[no]].l,e[j].flow);
					e[j].st++;
					u[++ni] = i;
				}
			}
		}
	}

	void step0() {	/* initialize */
		for (int i=0; i<m; i++)
			e[i].flow = 0;
		stpath();
	}

	void step1() {		/* mark an s-t path */
		int	i;

		if (v[tnode].dist<0)
			return;
		for (i = tnode; v[i].prev>=0; i=v[i].prev )
			e[v[i].p__Edge].st++;
	}

	void step2() {		/* augment the flow */
		int	i,j,a,f;

		f = v[tnode].l;
		for (i = tnode; (j=v[i].prev)>=0; i = j) {
			a = v[i].p__Edge;
			if (e[a].rndd_minus==i)
				e[a].flow += f;
			else if (e[a].rndd_plus==i)
				e[a].flow -= f;
		}
		stpath();
	}

	public void init() {
		String mdname = getParameter("inputfile");
		try {
			InputStream is;

			is = new URL(getDocumentBase(),mdname).openStream();
			input_graph(is);
			try {
				if (is != null)
					is.close();
				} catch(Exception e) {
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
		} catch (IOException e) {
			System.err.println("Cannot access file.");
		}

		String s = getParameter("s");
		if (s != null)
			snode = Integer.parseInt(s);
		else
			snode = 0;

		s = getParameter("t");
		if (s != null)
			tnode = Integer.parseInt(s);
		else
			tnode = n-1;

		setBackground(Color.white);
		rdb();
		step0();
	}

	public void paintNode(Graphics g, Node n, FontMetrics fm) {
		String s;
		int x = n.x;
		int y = n.y;
		int w = fm.stringWidth(n.name) + 10;
		int h = fm.getHeight() + 4;
		n.w = w;
		n.h = h;
		Color	c;

		if (n.dist<0)
			c = Color.gray;
		else
			c = Color.blue;
		g.setColor(c);
		g.drawRect(x-w/2,y-h/2,w,h);

		g.setColor(getBackground());
		g.fillRect(x-w/2+1,y-h/2+1,w-1,h-1);

		g.setColor(c);
		g.drawString(n.name,x-(w-10)/2,(y-(h-4)/2)+fm.getAscent());
	}

	int [] xy(int a, int b, int w, int h) {
		int	x[] = new int[2];

		if (Math.abs(w*b)>=Math.abs(h*a)) {
			x[0] = ((b>=0)?1:-1)*a*h/b/2;
			x[1] = ((b>=0)?1:-1)*h/2;
		} else {
			x[0] = ((a>=0)?1:-1)*w/2;
			x[1] = ((a>=0)?1:-1)*b*w/a/2;
		}
		return x;
	}

	void drawArrow(Graphics g,int x1,int y1,int x2,int y2) {
		int	a = x1-x2;
		int	b = y1-y2;

		double	aa = Math.sqrt(a*a+b*b)/16.0;
		double	bb = b/aa;
			aa = a/aa;
		g.drawLine(x2,y2,x2+(int)((aa*12+bb*5)/13),
				 y2+(int)((-aa*5+bb*12)/13));
		g.drawLine(x2,y2,x2+(int)((aa*12-bb*5)/13),
				 y2+(int)((aa*5+bb*12)/13));
		g.drawLine(x1,y1,x2,y2);
	}

	public void paint_Edge(Graphics g, _Edge e, FontMetrics fm) {
		Node v1 = v[e.rndd_plus];
		Node v2 = v[e.rndd_minus];
		Color	c;

		int a = v1.x-v2.x;
		int b = v1.y-v2.y;

		int x1[] = xy(-a,-b,v1.w,v1.h);
		int x2[] = xy(a,b,v2.w,v2.h);

		if (e.st>0)
			c = Color.red;
		else if ((v1.dist>=0)&&(v2.dist>=0))
			c = Color.blue;
		else
			c = Color.gray;
		g.setColor(c);
		drawArrow(g,v1.x+x1[0],v1.y+x1[1],v2.x+x2[0],v2.y+x2[1]);

		int w = fm.stringWidth(""+e.flow+"/"+e.capacity);
		int h = fm.getHeight();

		g.setColor(getBackground());
		g.fillRect((v1.x+v2.x-w)/2,(v1.y+v2.y-h)/2,w,h);
		g.setColor(Color.black);
		g.drawString(""+e.flow+"/"+e.capacity,
			(v1.x+v2.x-w)/2,(v1.y+v2.y-h)/2+fm.getAscent());
	}

	public void paint(Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		for (int i=0; i<n; i++)
			paintNode(g,v[i],fm);
		for (int i=0; i<m; i++)
			paint_Edge(g,e[i],fm);
		Residue p = (Residue)getAppletContext().getApplet("residue");
		if (p!=null)
			p.set(1,n,m,snode,tnode,v,e);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public boolean mouseDown(Event ev, int x, int y)
	{
		if (step==1) {
			step1();
			if (v[tnode].dist<0)
				step = 0;
			else
				step = 2;
		} else if (step==2){
			step2();
			step = 1;
		} else {
			step0();
			step = 1;
		}
		repaint();
		return true;
	}
}

