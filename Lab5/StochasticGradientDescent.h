
#ifndef __STOCHASTIC_GRADIENT_DESCENT__
#define __STOCHASTIC_GRADIENT_DESCENT__

#include "Algorithm.h"
#include <random>

class StochasticGradientDescent : public Algorithm {

private:
    double learningRate;
    int maxEpochs;
    double tolerance;
    int batchSize;

public:
    StochasticGradientDescent( int dim, double lr = 0.01, int maxE = 10000, double tol = 1e-6, int batch = 1 )
      : Algorithm( dim ), learningRate( lr ), maxEpochs( maxE ), tolerance( tol ), batchSize( batch ) {
    }

    Model solve( const Dataset & data ) override {
        std::vector<Record> d = data.getData();
        int n = d.size();
        if ( n == 0 ) return model;

        std::mt19937 rng{ std::random_device{}() };

        for ( int epoch = 0; epoch < maxEpochs; ++epoch ) {
            std::vector<Record> batch;
            int actualBatch = std::min( batchSize, n );
            std::sample( d.begin(), d.end(), std::back_inserter(batch), actualBatch, rng );

            int dim = batch[0].getInput().getDim();
            Vector grad( dim, 0 );

            for ( const Record & r : batch ) {
                Vector x = r.getInput();
                double y = r.getOutput();
                double pred = model.predict( x );
                double err = pred - y;
                Vector contrib = x.multiply( err );
                grad = grad.add( contrib );
            }

            grad = grad.divide( (double) actualBatch );

            if ( grad.norm() < tolerance ) break;

            model.update( grad, learningRate );
        }

        return model;
    }

};

#endif

