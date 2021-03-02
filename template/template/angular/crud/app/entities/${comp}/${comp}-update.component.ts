import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { I${ucomp}, ${ucomp} } from 'app/shared/model/${comp}.model';
import { ${ucomp}Service } from './${comp}.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';

@Component({
  selector: 'jhi-${lcomp}-update',
  templateUrl: './${comp}-update.component.html',
})
export class ${ucomp}UpdateComponent implements OnInit {
  isSaving = false;
  customers: ICustomer[] = [];

  editForm = this.fb.group({
    id: [],
    address1: [],
    adresss2: [],
    city: [],
    postcode: [null, [Validators.required, Validators.maxLength(10)]],
    country: [null, [Validators.required, Validators.maxLength(2)]],
    customer: [],
  });

  constructor(
    protected ${comp}Service: ${ucomp}Service,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ${comp} }) => {
      this.updateForm(${comp});

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));
    });
  }

  updateForm(${comp}: I${ucomp}): void {
    this.editForm.patchValue({
      id: ${comp}.id,
      address1: ${comp}.address1,
      address2: ${comp}.address2,
      city: ${comp}.city,
      postcode: ${comp}.postcode,
      country: ${comp}.country,
      customer: ${comp}.customer,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ${comp} = this.createFromForm();
    if (${comp}.id !== undefined) {
      this.subscribeToSaveResponse(this.${comp}Service.update(${comp}));
    } else {
      this.subscribeToSaveResponse(this.${comp}Service.create(${comp}));
    }
  }

  private createFromForm(): I${ucomp} {
    return {
      ...new ${ucomp}(),
      id: this.editForm.get(['id'])!.value,
      address1: this.editForm.get(['address1'])!.value,
      address2: this.editForm.get(['address2'])!.value,
      city: this.editForm.get(['city'])!.value,
      postcode: this.editForm.get(['postcode'])!.value,
      country: this.editForm.get(['country'])!.value,
      customer: this.editForm.get(['customer'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<I${ucomp}>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICustomer): any {
    return item.id;
  }
}
