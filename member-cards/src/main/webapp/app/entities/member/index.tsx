import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Member from './member';
import MemberDetail from './member-detail';
import MemberUpdate from './member-update';
import MemberDeleteDialog from './member-delete-dialog';

const MemberRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Member />} />
    <Route path="new" element={<MemberUpdate />} />
    <Route path=":id">
      <Route index element={<MemberDetail />} />
      <Route path="edit" element={<MemberUpdate />} />
      <Route path="delete" element={<MemberDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default MemberRoutes;
