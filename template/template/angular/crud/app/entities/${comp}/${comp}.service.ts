import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { I${ucomp} } from 'app/shared/model/${lcomp}.model';

type EntityResponseType = HttpResponse<I${ucomp}>;
type EntityArrayResponseType = HttpResponse<I${ucomp}[]>;

@Injectable({ providedIn: 'root' })
export class ${ucomp}Service {
  public resourceUrl = SERVER_API_URL + 'api/addresses';

  constructor(protected http: HttpClient) {}

  create(${lcomp}: I${ucomp}): Observable<EntityResponseType> {
    return this.http.post<I${ucomp}>(this.resourceUrl, ${lcomp}, { observe: 'response' });
  }

  update(${lcomp}: I${ucomp}): Observable<EntityResponseType> {
    return this.http.put<I${ucomp}>(this.resourceUrl, ${lcomp}, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<I${ucomp}>(`###@@{this.resourceUrl}/###@@{id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<I${ucomp}[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`###@@{this.resourceUrl}/###@@{id}`, { observe: 'response' });
  }
}
