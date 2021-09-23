# Handson ERFA BaseApp

## Step 1: Ressourcegruppe erstellen

https://docs.microsoft.com/en-us/azure/azure-resource-manager/management/manage-resource-groups-portal

## Step 2: Datenbank erstellen (optional)

https://docs.microsoft.com/en-us/azure/postgresql/quickstart-create-server-database-portal
https://docs.microsoft.com/en-us/answers/questions/34267/no-pg-hbaconf-entry-for-host-after-updating-connec.html

WICHTIG : Setzen von *Allow access to Azure services = Yes*

## Step 3: Datenbank füllen (optional)

psql "host=csa-baseapp-postgresql.postgres.database.azure.com port=5432 dbname=postgres user=csabaseapp@csa-baseapp-postgresql password=supergeheimespasswort4BaseApp sslmode=require" -f data.sql

WICHTIG: In Cloud Shell ausführen

## Step 4: Backend Services erstellen

https://docs.microsoft.com/en-us/azure/app-service/

WICHTIG: Je für BFF (Java 11), Decision (Java 11), DB (Java 11), UI (Node 14)

WICHTIG: Für Node Startup anpassen: https://nicolgit.github.io/how-deploy-angular-app-to-azure-appservice-running-linux-from-github/

## Step 5: Publish Profile downloaden und für Github Action verwenden

https://github.com/marketplace/actions/azure-webapp

WICHTIG: In Github Workflows App Name anpassen

WICHTIG: Connection Strings (BFF, Decision, DB, UI) anpassen
