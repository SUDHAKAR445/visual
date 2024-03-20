import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { registerLicense } from '@syncfusion/ej2-base';
import { AppModule } from './app/app.module';

registerLicense("Ngo9BigBOggjHTQxAR8/V1NBaF5cXmZCekx3THxbf1x0ZFRHallXTndeUiweQnxTdEFjWn1ecXVQT2FeV0B0Xg==");
platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
