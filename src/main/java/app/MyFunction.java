package app;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class MyFunction {
    //private double from;
    //private double to;
    public static ArrayList<Point> values = new ArrayList<Point>();

    public static void chartCalculate(double from, double to){
        values.clear();
        double x = from;
        double step = (to - from)/100;
        while(x < to){
            double y = getValue(x); //pow(x,3)-2.7*pow(x,2)+3.5*x+0.8;
            values.add(new Point(x, y));
            x+=step;
        }
    }

    private static double getValue(double x){
        return pow(x,3)-2.7*pow(x,2)-3.5*x+0.8;
    }

    public static double dichotomy(double a, double b, double epsilon, double delta){
        int i = 0;
        double from = a;
        double to = b;
        double c;
        //long start = System.currentTimeMillis();
        long start = System.nanoTime();
        do{
            i++;
            c = 0.5*(a+b);
            if((b-a)<2*epsilon) break;
            double curValue = getValue(c);
            if(abs(curValue)<delta) break;
            if(getValue(a)*curValue < 0) b=c;
            else a = c;
        }while(true);
        //long finish = System.currentTimeMillis();
        long finish = System.nanoTime();
        long timeConsumedMillis = finish - start;
        Logger.logTextArea.appendText("Dychotomy method:\n" + "from " + from + " to " + to + "\nepsilon = " + epsilon + "\ndelta = " + delta +
                "\nanswer = " + c + "\nmethod done with " + i + " iteration\n" + "in " + timeConsumedMillis + " ns.\n\n");
        //Logger.printReport();
        return c;
    }

    public static double newton(double approx, double epsilon){
        double startApprox = approx;
        double curApprox = approx;
        int i = 0;
        long startN = System.currentTimeMillis();
        do {
            approx = curApprox;
            curApprox = approx - getValue(approx) / getDerivative(approx, 0.001);
            i++;
        }while(abs(curApprox - approx)>=epsilon);
        long finishN = System.currentTimeMillis();
        long timeConsumedMillisN = finishN - startN;
        Logger.logTextArea.appendText("Newton's method:\n" + "starting approximation = " + startApprox + "\nepsilon = " + epsilon +
                "\nanswer = " + curApprox + "\nmethod done with " + i + " iteration\n" + "in " + timeConsumedMillisN + " ms.\n\n");
        return curApprox;
    }

    private static double getDerivative(double x, double derivStep){
        return (getValue(x+derivStep) - getValue(x))/derivStep;
    }
}
