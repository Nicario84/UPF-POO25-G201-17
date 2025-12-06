
#ifndef __GRADIENT_DESCENT__
#define __GRADIENT_DESCENT__

#include "Algorithm.h"

class GradientDescent : public Algorithm {

private:
    double learningRate;  // tasa de aprendizaje (eta)
    int maxEpochs;        // número máximo de iteraciones
    double tolerance;     // criterio de parada por norma del gradiente

public:
    
   GradientDescent( int dim, double lr = 0.01, int maxE = 10000, double tol = 1e-6 )
      : Algorithm( dim ), learningRate( lr ), maxEpochs( maxE ), tolerance( tol ) {
    }

    Model solve( const Dataset & data ) override {
        
        // Obtiene todos los registros del dataset
        std::vector<Record> d = data.getData();
        int n = d.size();
        
        // Si no hay datos, devolvemos el modelo inicial
        if ( n == 0 )
            return model;

        // Extraemos la dimensión de los vectores de entrada
        int dim = d[0].getInput().getDim();

        // Ciclo principal de entrenamiento
        for ( int epoch = 0; epoch < maxEpochs; ++epoch ) {

            // Inicializar gradiente a vector cero
            Vector grad( dim, 0 );

            // Recorremos todo el dataset
            for ( int i = 0; i < n; ++i ) {

                // x: entrada, y: salida real
                Vector x = d[i].getInput();
                double y = d[i].getOutput();

                // predicción del modelo actual
                double pred = model.predict( x );

                // error: (pred - real)
                double err = pred - y;

                // contribución del gradiente: err * x
                Vector contribution = x.multiply( err );

                // acumulamos
                grad = grad.add( contribution );
            }

            // Media del gradiente: (1/n)
            grad = grad.divide( (double) n );

            // Criterio de parada: gradiente muy pequeño
            if ( grad.norm() < tolerance )
                break;

            // Actualización de parámetros:
            // params = params - learningRate * grad
            model.update( grad, learningRate );
        }

        return model;
    }
};

#endif

