package org.infinispan.query.remote.impl.filter;

import org.infinispan.filter.KeyValueFilterConverter;
import org.infinispan.filter.NamedFactory;
import org.infinispan.filter.ParamKeyValueFilterConverterFactory;
import org.kohsuke.MetaInfServices;

import java.util.Map;

import static org.infinispan.query.remote.impl.filter.JPAFilterConverterUtils.unmarshallJPQL;
import static org.infinispan.query.remote.impl.filter.JPAFilterConverterUtils.unmarshallParams;


/**
 * Factory for a {@link KeyValueFilterConverter} that operates on binary parameters and produces binary results.
 *
 * @author gustavonalle
 * @since 8.1
 */
@NamedFactory(name = JPAProtobufFilterAndConverterFactory.FACTORY_NAME)
@MetaInfServices
@SuppressWarnings("unused")
public final class JPAProtobufFilterAndConverterFactory implements ParamKeyValueFilterConverterFactory {

   public static final String FACTORY_NAME = "iteration-filter-converter-factory";

   @Override
   public KeyValueFilterConverter getFilterConverter(Object[] params) {
      String jpql = unmarshallJPQL(params);
      Map<String, Object> namedParams = unmarshallParams(params);
      return new JPABinaryProtobufFilterAndConverter<>(jpql, namedParams);
   }

   @Override
   public boolean binaryParam() {
      return true;
   }
}
