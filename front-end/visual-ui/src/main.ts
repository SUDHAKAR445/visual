import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { registerLicense } from '@syncfusion/ej2-base';
import { AppModule } from './app/app.module';

registerLicense("Ngo9BigBOggjHTQxAR8/V1NAaF1cXmhKYVF2WmFZfVpgdl9EYVZUTWYuP1ZhSXxXdkZiWn9ccnVQT2BbUEI=");
platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
