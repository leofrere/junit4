package org.junit.experimental.theories.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

public class SpecificDataPointsSupplier extends AllMembersSupplier {

    public SpecificDataPointsSupplier(TestClass testClass) {
        super(testClass);
    }
    
    @Override
    protected Collection<Field> getSingleDataPointFields(ParameterSignature signature) {
        Collection<Field> fields = super.getSingleDataPointFields(signature);        
        String requestedName = signature.getAnnotation(FromDataPoints.class).value();

        List<Field> fieldsWithMatchingNames = new ArrayList<Field>();
        
        for (Field field : fields) {
            String[] fieldNames = field.getAnnotation(DataPoint.class).value();
            if (Arrays.asList(fieldNames).contains(requestedName)) {
                fieldsWithMatchingNames.add(field);
            }
        }
        
        return fieldsWithMatchingNames;
    }
    
    @Override
    protected Collection<Field> getDataPointsFields(ParameterSignature signature) {
        Collection<Field> fields = super.getDataPointsFields(signature);        
        String requestedName = signature.getAnnotation(FromDataPoints.class).value();
        
        List<Field> fieldsWithMatchingNames = new ArrayList<Field>();
        
        for (Field field : fields) {
            String[] fieldNames = field.getAnnotation(DataPoints.class).value();
            if (Arrays.asList(fieldNames).contains(requestedName)) {
                fieldsWithMatchingNames.add(field);
            }
        }
        
        return fieldsWithMatchingNames;
    }
    
    @Override
    protected Collection<FrameworkMethod> getSingleDataPointMethods(ParameterSignature signature) {
        Collection<FrameworkMethod> methods = super.getSingleDataPointMethods(signature);
        String requestedName = signature.getAnnotation(FromDataPoints.class).value();
        
        List<FrameworkMethod> methodsWithMatchingNames = new ArrayList<FrameworkMethod>();
        
        for (FrameworkMethod method : methods) {
            String[] methodNames = method.getAnnotation(DataPoint.class).value();
            if (Arrays.asList(methodNames).contains(requestedName)) {
                methodsWithMatchingNames.add(method);
            }
        }
        
        return methodsWithMatchingNames;
    }
    
    @Override
    protected Collection<FrameworkMethod> getDataPointsMethods(ParameterSignature signature) {
        Collection<FrameworkMethod> methods = super.getDataPointsMethods(signature);
        String requestedName = signature.getAnnotation(FromDataPoints.class).value();
        
        List<FrameworkMethod> methodsWithMatchingNames = new ArrayList<FrameworkMethod>();
        
        for (FrameworkMethod method : methods) {
            String[] methodNames = method.getAnnotation(DataPoints.class).value();
            if (Arrays.asList(methodNames).contains(requestedName)) {
                methodsWithMatchingNames.add(method);
            }
        }
        
        return methodsWithMatchingNames;
    }

}
