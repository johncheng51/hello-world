import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { I${ucomp}, ${ucomp} } from 'app/shared/model/${comp}.model';
import { ${ucomp}Service } from './${comp}.service';
import { ${ucomp}Component } from './${comp}.component';
import { ${ucomp}DetailComponent } from './${comp}-detail.component';
import { ${ucomp}UpdateComponent } from './${comp}-update.component';

@Injectable({ providedIn: 'root' })
export class ${ucomp}Resolve implements Resolve<I${ucomp}> {
  constructor(private service: ${ucomp}Service, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<I${ucomp}> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((${comp}: HttpResponse<${ucomp}>) => {
          if (${comp}.body) {
            return of(${comp}.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ${ucomp}());
  }
}

export const ${comp}Route: Routes = [
  {
    path: '',
    component: ${ucomp}Component,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: '${ucomp}es',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ${ucomp}DetailComponent,
    resolve: {
      ${comp}: ${ucomp}Resolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: '${ucomp}es',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ${ucomp}UpdateComponent,
    resolve: {
      ${comp}: ${ucomp}Resolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: '${ucomp}es',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ${ucomp}UpdateComponent,
    resolve: {
      ${comp}: ${ucomp}Resolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: '${ucomp}es',
    },
    canActivate: [UserRouteAccessService],
  },
];
