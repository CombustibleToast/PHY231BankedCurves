import java.io.*;
public class BankedCurvesCalculations{
    public static void main(String[] args)throws IOException{
        final double massOfVehicle = 1093;
        final double heightOfCenterOfGravity = 0.536;
        final double wheelbaseLength = 1.3;
        final double[] linearVelocities = new double[]{6.7,13.4,22.4,31.3};
        final double radiusOfCurve = 50;
        final double[] coefficientsOfFriction = new double[]{0.75,0.25,0.05}; //,0.25,0.05
        final double[] anglesOfBank = new double[]{0,5,10,15,20}; //,5,10,15,20}

        PrintWriter out;

        for(int i = 0; i < coefficientsOfFriction.length; i++){
            out = new PrintWriter(new File("outputFriction_" + coefficientsOfFriction[i] + ".txt"));
            for(int j = 0; j < linearVelocities.length; j++){
                for(int k = 0; k < anglesOfBank.length; k++){
                    out.println("Calculating using the variables:");
                    out.println("Coefficient of friction:    " + coefficientsOfFriction[i]);
                    out.println("Vehicle speed (m/s):        " + linearVelocities[j]);
                    out.printf("%s%.1f%n", "Vehicle speed (mph):        ", linearVelocities[j]*2.237);
                    out.println("Angle of bank of the curve: " + anglesOfBank[k]);
                    out.println("~~~");

                    double centripetalForceRequired = (massOfVehicle * Math.pow(linearVelocities[j],2))/radiusOfCurve;
                    out.printf("%s%.1f%s%n", "Centripetal force required: ", centripetalForceRequired, " N");
                    double normalForce = massOfVehicle * 9.8 * Math.cos(anglesOfBank[k] * (Math.PI/180));
                    out.printf("%s%.1f%s%n", "Normal Force on Vehicle: ", normalForce, " N");
                    double forceOfFriction = (coefficientsOfFriction[i] * normalForce);
                    out.printf("%s%.1f%s%n", "Force of friction produced: ", forceOfFriction, " N");
                    //Calculation of banked normal force stuff goes here, edit line below to reflect
                    out.printf("%s%.1f%s%n%n", "Difference (Negative means friction is not enough and the car will slide out): ", forceOfFriction - centripetalForceRequired, " N");

                    double maxSafeSpeed = Math.sqrt((9.8*radiusOfCurve*((wheelbaseLength/2) + Math.sin(anglesOfBank[k] * (Math.PI/180))))/heightOfCenterOfGravity);
                    double frictionTorqueProduced = forceOfFriction * heightOfCenterOfGravity;
                    out.printf("%s%.1f%s%n", "Torque produced due to friction: ", frictionTorqueProduced, " N*m");
                    out.printf("%s%.1f%s%n", "Maximum speed before tipping: ", maxSafeSpeed, " m/s");
                    out.printf("%s%.1f%s%n", "Maximum speed before tipping: ", maxSafeSpeed * 2.237, " mph");
                    out.printf("%s%.1f%s%n", "Difference in speeds (negative means the car has tipped): ", maxSafeSpeed - linearVelocities[j], " m/s");
                    out.printf("%s%.1f%s%n", "Difference in speeds (negative means the car has tipped): ", (maxSafeSpeed - linearVelocities[j]) * 2.237, " mph");
                    out.println("\n\n");
                }
            }
            out.close();
        }
    }
}