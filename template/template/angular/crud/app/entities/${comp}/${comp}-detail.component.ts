import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { I${ucomp} } from 'app/shared/model/${comp}.model';

@Component({
  selector: 'jhi-${lcomp}-detail',
  templateUrl: './${comp}-detail.component.html',
})
export class ${ucomp}DetailComponent implements OnInit {
  ${comp}: I${ucomp} | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ${comp} }) => (this.${comp} = ${comp}));
  }

  previousState(): void {
    window.history.back();
  }
}
