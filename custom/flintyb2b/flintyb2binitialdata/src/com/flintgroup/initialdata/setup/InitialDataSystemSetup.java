/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.flintgroup.initialdata.setup;

import com.flintgroup.initialdata.impl.FlintSampleDataImportService;
import de.hybris.platform.commerceservices.dataimport.impl.CoreDataImportService;
import de.hybris.platform.commerceservices.dataimport.impl.SampleDataImportService;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import com.flintgroup.initialdata.constants.Flintyb2bInitialDataConstants;

import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.commerceservices.setup.events.CoreDataImportedEvent;
import de.hybris.platform.commerceservices.setup.events.SampleDataImportedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * This class provides hooks into the system's initialization and update processes.
 * 
 * @see "https://wiki.hybris.com/display/release4/Hooks+for+Initialization+and+Update+Process"
 */
@SystemSetup(extension = Flintyb2bInitialDataConstants.EXTENSIONNAME)
public class InitialDataSystemSetup extends AbstractSystemSetup
{
	//public static final String FLINT = "flint";
	public static final String FLINTGROUP = "flintgroup";
	public static final String FLINTGROUP_ZH = "flintgroup-zh";
	private static final String IMPORT_CORE_DATA = "importCoreData";
	private static final String IMPORT_SAMPLE_DATA = "importSampleData";
	private static final String ACTIVATE_SOLR_CRON_JOBS = "activateSolrCronJobs";
	public static final String COMMON_DATA = "commonData";
	private static final String IMPORT_REFERENCE_DATA = "importReferenceData";
   private static final String IMPORT_FLINT_US_SITE_DATA = "importFlintUSSiteData";
   private static final String IMPORT_FLINT_ZH_SITE_DATA = "importFlintZHSiteData";
   private static final String IMPORT_SOLR_CONFIG = "importSolrConfig";
   private static final String FLINT_IMPORT_HOME = "/flintyb2binitialdata/import";
   private static final String IMPORT_FLINT_CONTENT_DATA = "importFlintSampleContentData";
   private static final String IMPORT_FLINT_ZH_CONTENT_DATA = "importFlintZHSampleContentData";
   private static final String IMPORT_FLINT_PRODUCT_DATA = "importFlintSampleProductData";
   private static final String IMPORT_FLINT_ZH_PRODUCT_DATA = "importFlintZHSampleProductData";
   private static final String IMPORT_FLINT_CATALOG_SYNCH_JOBS = "importFlintCatalogSynchJobs";
   private static final String IMPORT_FLINT_ZH_CATALOG_SYNCH_JOBS = "importFlintZHCatalogSynchJobs";
   public String COMMA_SEPERATED_CONTENT_CATALOGS = "";
   public static final String COMMA = ",";
   
	private CoreDataImportService coreDataImportService;
	private FlintSampleDataImportService flintSampleDataImportService;

	@SystemSetupParameterMethod
	@Override
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		params.add(createBooleanSystemSetupParameter(IMPORT_CORE_DATA, "Import Core Data", true));
		params.add(createBooleanSystemSetupParameter(IMPORT_SAMPLE_DATA, "Import Sample Data", true));
		params.add(createBooleanSystemSetupParameter(ACTIVATE_SOLR_CRON_JOBS, "Activate Solr Cron Jobs", true));
		params.add(createBooleanSystemSetupParameter(IMPORT_REFERENCE_DATA, "Import Reference Data",
            true));
		 params.add(createBooleanSystemSetupParameter(COMMON_DATA, "Common Data", true));
		 params.add(createBooleanSystemSetupParameter(IMPORT_SOLR_CONFIG,
             "Import Solr Config", true));
		 params.add(createBooleanSystemSetupParameter(IMPORT_FLINT_US_SITE_DATA, "Import Flint US Site Data",
                 false));
     params.add(createBooleanSystemSetupParameter(IMPORT_FLINT_ZH_SITE_DATA, "Import Flint ZH Site Data",
             true));
     params.add(createBooleanSystemSetupParameter(IMPORT_FLINT_CONTENT_DATA,
           "Import Flint US Catalog Sample Data", false));
     params.add(createBooleanSystemSetupParameter(IMPORT_FLINT_ZH_CONTENT_DATA,
           "Import Flint ZH Catalog Sample Data", true));
     params.add(createBooleanSystemSetupParameter(IMPORT_FLINT_PRODUCT_DATA,
           "Import Flint US Product Sample Data", false));
     params.add(createBooleanSystemSetupParameter(IMPORT_FLINT_ZH_PRODUCT_DATA,
           "Import Flint ZH Product Sample Data", true));
     params.add(createBooleanSystemSetupParameter(IMPORT_FLINT_CATALOG_SYNCH_JOBS,
           "Import Flint US Product Sample Data", false));
     params.add(createBooleanSystemSetupParameter(IMPORT_FLINT_ZH_CATALOG_SYNCH_JOBS,
           "Import Flint ZH Product Sample Data", true));
		
		return params;
	}

	/**
	 * This method will be called during the system initialization.
	 *
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = SystemSetup.Type.PROJECT, process = SystemSetup.Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{
		final List<ImportData> importData = new ArrayList<ImportData>();

		final ImportData flintImportData = new ImportData();
	/*	flintImportData.setProductCatalogName(FLINT);
		flintImportData.setContentCatalogNames(Arrays.asList(FLINT));
		flintImportData.setStoreNames(Arrays.asList(FLINT));*/
		
		COMMA_SEPERATED_CONTENT_CATALOGS = FLINTGROUP + COMMA + FLINTGROUP_ZH;
		flintImportData.setProductCatalogName(FLINTGROUP);
		flintImportData.setContentCatalogNames(
                Arrays.asList(COMMA_SEPERATED_CONTENT_CATALOGS.split(COMMA)));
		flintImportData.setStoreNames(Arrays.asList(FLINTGROUP));
		
      importData.add(flintImportData);


		getCoreDataImportService().execute(this, context, importData);
		getEventService().publishEvent(new CoreDataImportedEvent(context, importData));
		
		 if (this.getBooleanSystemSetupParameter(context, IMPORT_FLINT_CONTENT_DATA)) {
          // Flint US core
			 
			 importImpexFile(context, FLINT_IMPORT_HOME
                + "/coredata/contentCatalogs/flintgroupContentCatalog/catalog.impex");
			 
			 importImpexFile(context, FLINT_IMPORT_HOME
                + "/coredata/contentCatalogs/flintgroupContentCatalog/catalog_en.impex");
			 
          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/coredata/contentCatalogs/flintgroupContentCatalog/cms-content.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/coredata/contentCatalogs/flintgroupContentCatalog/cms-content_en.impex");
          
          importImpexFile(context, FLINT_IMPORT_HOME
                + "/coredata/contentCatalogs/flintgroupContentCatalog/cms-content-secureportal.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/coredata/contentCatalogs/flintgroupContentCatalog/email-content.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/coredata/contentCatalogs/flintgroupContentCatalog/email-content_en.impex");

          // Flint US sample
          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/sampledata/contentCatalogs/flintgroupContentCatalog/cms-content.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/sampledata/contentCatalogs/flintgroupContentCatalog/cms-content_en.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/sampledata/contentCatalogs/flintgroupContentCatalog/email-content.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/sampledata/contentCatalogs/flintgroupContentCatalog/email-content_en.impex");

      }
		 if (this.getBooleanSystemSetupParameter(context, IMPORT_FLINT_ZH_CONTENT_DATA)) {
          // Flint ZH core
			 
			 importImpexFile(context, FLINT_IMPORT_HOME
                + "/coredata/contentCatalogs/flintgroupContentCatalog/catalog.impex");
			 
			 importImpexFile(context, FLINT_IMPORT_HOME
                + "/coredata/contentCatalogs/flintgroupContentCatalog/catalog_zh.impex");
			 
          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/coredata/contentCatalogs/flintgroup-zhContentCatalog/cms-content.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/coredata/contentCatalogs/flintgroup-zhContentCatalog/cms-content_zh.impex");
          
          importImpexFile(context, FLINT_IMPORT_HOME
                + "/coredata/contentCatalogs/flintgroup-zhContentCatalog/cms-content-secureportal_zh.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/coredata/contentCatalogs/flintgroup-zhContentCatalog/email-content.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/coredata/contentCatalogs/flintgroup-zhContentCatalog/email-content_zh.impex");

          // Flint ZH sample
          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/sampledata/contentCatalogs/flintgroup-zhContentCatalog/cms-content.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/sampledata/contentCatalogs/flintgroup-zhContentCatalog/cms-content_zh.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/sampledata/contentCatalogs/flintgroup-zhContentCatalog/email-content.impex");

          importImpexFile(context, FLINT_IMPORT_HOME
                  + "/sampledata/contentCatalogs/flintgroup-zhContentCatalog/email-content_zh.impex");

      }

      if (this.getBooleanSystemSetupParameter(context, COMMON_DATA)) {

          // Import essential data
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/common/essential-data.impex",
                  false);
      }

      if (this.getBooleanSystemSetupParameter(context, IMPORT_FLINT_US_SITE_DATA)) {
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup/store.impex");
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup/site.impex");
          /*importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup//site_media.impex");*/
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup/solr.impex");
          
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup/solrtrigger.impex");
          
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup/store-responsive.impex");
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup/site-responsive.impex");
          
        /*  importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup/warehouses.impex");
         */
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup/site_en.impex");
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup/solr_en.impex");
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup/store_en.impex");
          
          importImpexFile(context, FLINT_IMPORT_HOME + "/sampledata/stores/flintgroup-zh/solr.impex");
          importImpexFile(context, FLINT_IMPORT_HOME + "/sampledata/stores/flintgroup-zh/solr_en.impex");
          //importImpexFile(context, FLINT_IMPORT_HOME + "/sampledata/stores/flintgroup-zh/warehouses.impex");
      }

      if (this.getBooleanSystemSetupParameter(context, IMPORT_FLINT_ZH_SITE_DATA)) {
      	
          importImpexFile(context, FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/store.impex");
          importImpexFile(context, FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/site.impex");
          
        /*  importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/site_media.impex");
*/
          importImpexFile(context, FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/solr.impex");
          
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/solrtrigger.impex");
          
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/store-responsive.impex");
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/site-responsive.impex");
         /* importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/warehouses.impex");*/
          

          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/site_zh.impex");
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/solr_zh.impex");
          importImpexFile(context,
         		 FLINT_IMPORT_HOME + "/coredata/stores/flintgroup-zh/store_zh.impex");
          
          importImpexFile(context, FLINT_IMPORT_HOME + "/sampledata/stores/flintgroup-zh/solr.impex");
          importImpexFile(context, FLINT_IMPORT_HOME + "/sampledata/stores/flintgroup-zh/solr_zh.impex");
          
          
      }
      
      if (this.getBooleanSystemSetupParameter(context, IMPORT_FLINT_PRODUCT_DATA)) {

         importImpexFile(context,
        		 FLINT_IMPORT_HOME + "/coredata/productCatalogs/flintgroupProductCatalog/catalog.impex");
         importImpexFile(context,
          		 FLINT_IMPORT_HOME + "/coredata/productCatalogs/flintgroupProductCatalog/catalog_en.impex");
         
         importImpexFile(context,
          		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroupProductCatalog/categories.impex");
           importImpexFile(context,
            		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroupProductCatalog/categories_en.impex");
           
           importImpexFile(context,
            		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroupProductCatalog/categories-media.impex");           
            
           importImpexFile(context,
           		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroupProductCatalog/products.impex");
            importImpexFile(context,
             		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroupProductCatalog/products_en.impex");
            importImpexFile(context,
           		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroupProductCatalog/products-media.impex");
     }
      
      if (this.getBooleanSystemSetupParameter(context, IMPORT_FLINT_ZH_PRODUCT_DATA)) {

         importImpexFile(context,
        		 FLINT_IMPORT_HOME + "/coredata/productCatalogs/flintgroup-zhProductCatalog/catalog.impex");
         importImpexFile(context,
          		 FLINT_IMPORT_HOME + "/coredata/productCatalogs/flintgroup-zhProductCatalog/catalog_zh.impex");
         
         importImpexFile(context,
          		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroup-zhProductCatalog/categories.impex");
           importImpexFile(context,
            		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroup-zhProductCatalog/categories_zh.impex");
           
           importImpexFile(context,
           		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroup-zhProductCatalog/categories-media.impex");
                    
           
           importImpexFile(context,
           		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroup-zhProductCatalog/products.impex");
                    
            importImpexFile(context,
             		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroup-zhProductCatalog/products_zh.impex");
                      
            importImpexFile(context,
             		 FLINT_IMPORT_HOME + "/sampledata/productCatalogs/flintgroup-zhProductCatalog/products-media.impex");
                      
     }
    
      if (this.getBooleanSystemSetupParameter(context, IMPORT_FLINT_CATALOG_SYNCH_JOBS)) {

      	 importImpexFile(context, FLINT_IMPORT_HOME
                + "/coredata/contentCatalogs/flintgroupContentCatalog/catalogs-sync.impex");
      	 importImpexFile(context, FLINT_IMPORT_HOME 
      			 + "/coredata/productCatalogs/flintgroupProductCatalog/catalogs-sync.impex");
			 
                 
     }
      if (this.getBooleanSystemSetupParameter(context, IMPORT_FLINT_ZH_CATALOG_SYNCH_JOBS)) {

      	 importImpexFile(context, FLINT_IMPORT_HOME
                + "/coredata/contentCatalogs/flintgroup-zhContentCatalog/catalogs-sync.impex");
      	 
      	 importImpexFile(context,
           		 FLINT_IMPORT_HOME + "/coredata/productCatalogs/flintgroup-zhProductCatalog/catalogs-sync.impex");
			 
                 
     }

		/*getFlintSampleDataImportService().execute(this, context, importData);
		getFlintSampleDataImportService().importCommerceOrgData(context);
		getEventService().publishEvent(new SampleDataImportedEvent(context, importData));*/

        // Employee Users - CMS Cockpit
        importImpexFile(context,
                FLINT_IMPORT_HOME + "/sampledata/cockpits/cmscockpit/cmscockpit-users.impex");
        importImpexFile(context,
                FLINT_IMPORT_HOME + "/sampledata/cockpits/cscockpit/cscockpit-users.impex");
        importImpexFile(context,
                FLINT_IMPORT_HOME + "/sampledata/cockpits/productcockpit/productcockpit-users.impex");


    }

	public CoreDataImportService getCoreDataImportService()
	{
		return coreDataImportService;
	}

	@Required
	public void setCoreDataImportService(final CoreDataImportService coreDataImportService)
	{
		this.coreDataImportService = coreDataImportService;
	}


	public FlintSampleDataImportService getFlintSampleDataImportService()
	{
		return flintSampleDataImportService;
	}

	@Required
	public void setFlintSampleDataImportService(final FlintSampleDataImportService flintSampleDataImportService)
	{
		this.flintSampleDataImportService = flintSampleDataImportService;
	}


}
