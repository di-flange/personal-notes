#include <math.h>

#define PI 3.1415926536

namespace geom
{
    class Point
    {
    private:
        double x;
        double y;
    }

    /**
	 * Class of line.
	 */
	class Line
	{
	public:
        Line()
        {

        }

        Line(Point p1, Point p2)
        {
            this->p1 = p1;
            this->p2 = p2;
        }
        
        Line(float x1, float y1, float x2, float y2)
        {
            Point p1(x1, y1);
            Point p2(x2, y2);
            
            this->p1 = p1;
            this->p2 = p2;
        }
        
	private:
		Point p1;
		Point p2;
	};

	/**
	 * Class of circle.
	 */
	class Circle
	{
		Point center;
		float r;
	};

	/**
	 * Polygon.
	 */
	class Polygon
	{
	public:
		Polygon(int corners)
		{
			this->corners = new Point[corners];
		}

		~Polygon(void)
		{
			delete []this->corners;
		}
	private:
		Point *corners;
	}; 

	/**
	 * Class of geometrical vectors.
	 */
	class VectGeom
	{
	public:
		VectGeom(void)
		{

		}

		VectGeom(float x, float y)
		{
			this->x = x;
			this->y = y;
		}

		float getX(void)
		{
			return this->x;
		} 

		float getY(void)
		{
			return this->y;
		} 

		float setX(float x)
		{
			this->x = x;
		} 

		float setY(float y)
		{
			this->y = y;
		}

		/**
		 * Angle between two vectors.
		 */
		static int angle(VectGeom &a, VectGeom &b)
		{
			int sp=a.x * b.x + a.y * b.y;
			int psp= a.x * b.y – a.y * b.x;

			float angle;

			if (!sp)
			{
				if (psp > 0)
				{
					angle = pi/2;
				}
				else
				{
					angle = -pi/2;
				}
			}
			else
			{
				angle=atan((float)psp/sp);

				if (sp<0)
				{
					if (psp<0)
					{
						angle-=pi;
					}
					else
					{
						angle+=pi;
					}
				}
			}

			angle=angle/pi*180;

			cout<<"a="<<angle<<endl;   
			cout<<"a2="<<(atan2(by,bx)-atan2(ay,ax))/pi*180<<endl;
		}
	private:
		float x;
		float y;
	};
}


void printArr(std::ostream &stream, float *arr, int size)
{
	for (int i = 0; i < size; i++)
	{
		stream << arr[i] << "  ";
	}

	stream << std::endl;
}

void replace(float *x, float *y)
{
	// Create a buffer cell.
    float b;

	// Move items. 
	b  = *x;
	*x = *y;
	*y = b;
}

void qSort(float *arr, int size)
{
    int limLeft  = -1;   
    int limRight = size;

    float mediane = arr[limRight / 2];
    
    while (++limLeft < --limRight)
    {
        // Search limits
        for (; arr[limLeft]  < mediane; limLeft++);
        for (; arr[limRight] > mediane; limRight--);        
        
        // Replace items
	    if (limLeft < limRight)
	    {
	        replace(arr + limLeft, arr + limRight);
	    }
	    else
	    {
	        if (limLeft == limRight)
	        {
	            ++limLeft;
	            --limRight;
	        }
	        
	        break;
	    }
	    
    }

    if (limRight > 0)
    {
        qSort(arr, limRight + 1);
    }
    
    if (limLeft < size - 1)
    {
        qSort(arr + limLeft, size - limLeft);
    }
}