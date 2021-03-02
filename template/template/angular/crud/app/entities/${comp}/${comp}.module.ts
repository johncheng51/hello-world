import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AngularSharedModule } from 'app/shared/shared.module';
import { ${ucomp}Component } from './${comp}.component';
import { ${ucomp}DetailComponent } from './${comp}-detail.component';
import { ${ucomp}UpdateComponent } from './${comp}-update.component';
import { ${ucomp}DeleteDialogComponent } from './${comp}-delete-dialog.component';
import { ${comp}Route } from './${comp}.route';

@NgModule({
  imports: [AngularSharedModule, RouterModule.forChild(${comp}Route)],
  declarations: [${ucomp}Component, 
                 ${ucomp}DetailComponent, 
                 ${ucomp}UpdateComponent, 
                 ${ucomp}DeleteDialogComponent],
  entryComponents: [${ucomp}DeleteDialogComponent],
})
export class Angular${ucomp}Module {}
