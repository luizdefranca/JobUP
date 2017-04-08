using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JOB.StartUp
{
    public static class DependencyResolver
    {
        public static void Resolve(UnityContainer container)
        {
            //container.RegisterType<DeploySEContext, DeploySEContext>(new HierarchicalLifetimeManager());

            container.RegisterType<IProjetoAppService, ProjetoAppService>(new HierarchicalLifetimeManager());
            container.RegisterType<IBranchAppService, BranchAppService>(new HierarchicalLifetimeManager());
            container.RegisterType<IDestinoAppService, DestinoAppService>(new HierarchicalLifetimeManager());
            container.RegisterType<IHistoricoAppService, HistoricoAppService>(new HierarchicalLifetimeManager());
            container.RegisterType<IHistoricoCommitAppService, HistoricoCommitAppService>(new HierarchicalLifetimeManager());

            container.RegisterType<IProjetoService, ProjetoService>(new HierarchicalLifetimeManager());
            container.RegisterType<IBranchService, BranchService>(new HierarchicalLifetimeManager());
            container.RegisterType<IDestinoService, DestinoService>(new HierarchicalLifetimeManager());
            container.RegisterType<IHistoricoService, HistoricoService>(new HierarchicalLifetimeManager());
            container.RegisterType<IHistoricoCommitService, HistoricoCommitService>(new HierarchicalLifetimeManager());

            container.RegisterType<IProjetoRepository, ProjetoRepository>(new HierarchicalLifetimeManager());
            container.RegisterType<IBranchRepository, BranchRepository>(new HierarchicalLifetimeManager());
            container.RegisterType<IDestinoRepository, DestinoRepository>(new HierarchicalLifetimeManager());
            container.RegisterType<IHistoricoRepository, HistoricoRepository>(new HierarchicalLifetimeManager());
            container.RegisterType<IHistoricoCommitRepository, HistoricoCommitRepository>(new HierarchicalLifetimeManager());
        }
    }
}
