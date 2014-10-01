package csc130nim;

import java.io.Serializable;

public class Board implements Serializable{

	private int a, b, c;
	
	public Board(){}
	
	public Board(int a, int b, int c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public int get(int index)
	{
		switch(index)
		{
		case(0):
			return a;
		case(1):
			return b;
		case(2):
			return c;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	public void set(int index, int value)
	{
		switch(index)
		{
		case(0):
			a = value;
			break;
		case(1):
			b = value;
			break;
		case(2):
			c = value;
			break;
		default:
			throw new IndexOutOfBoundsException();
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		result = prime * result + c;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		if (c != other.c)
			return false;
		return true;
	}
	
	@Override
	public Board clone()
	{
		return new Board(a, b, c);
	}
	
	@Override
	public String toString()
	{
		return "[" + a + ", " + b + ", " + c + "]";
	}
	
}
