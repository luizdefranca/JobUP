using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.DATA.ValueObject;
using JOB.WEB.Controllers;
using JOB.WEB.Models;
using NUnit.Framework;
using System;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Tests.Controllers
{
    [TestFixture]
    public class UsuarioControllerTest
    {
        private Contexto ctx = new Contexto();

        [SetUp]
        public void Initialize()
        {
            Mapper.Initialize(cfg =>
            {
                cfg.CreateMap<USUARIO, UsuarioViewModel>();
            });
        }

        [Test]
        public void Integration_ValidarInsertUsuario()
        {
            Guid id = Guid.NewGuid();

            //gera uma nova classe para testes
            var domain = new USUARIO(id, "USUARIO TESTE", new CPF("50869388720"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("(81) 33793968"), new Telefone("(81) 994059945"), new Email("umteste1@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            try
            {
                //se comunica com o controller
                var ctx = new Contexto();
                ctx.Database.BeginTransaction(); //inicia uma transação para controlar os dados manipulados no banco de dados

                var controller = new ManageController(ctx);
                controller.ProcessarCadastro(model, id);

                //recupera o novo usuario inserido no banco
                var domainNew = ctx.Usuario.First(w => w.CPF.NR == "50869388720");

                ctx.Database.CurrentTransaction.Rollback(); //retorna qualquer mudança feita no banco de dados

                //teste finalmente se existe usuario inserido no banco e é o mesmo gerado no inicio do método
                Assert.AreEqual(model.CPF, domainNew.CPF.NR);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        [Test]
        public void Integration_InsertUsuarioNomeFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone(""), new Email("umteste2@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioCPFFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "USUARIO DE TESTE", new CPF(""), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste3@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioRGFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste4@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioTelefoneFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone(""), new Telefone("994059945"), new Email("umteste5@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioCelularFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone(""), new Email("umteste6@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioEmailFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email(""));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioCEPFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste7@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioRuaFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste8@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioBairroFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste2@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Bairro", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioCidadeFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste2@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioTodosDadosFaltando()
        {
            var domain = new USUARIO(Guid.NewGuid(), "", new CPF(""), new RG(DATA.Enum.EnumUF.PE, ""), DateTime.Now.AddYears(-0));
            domain.AdicionarContato(new Telefone(""), new Telefone(""), new Email(""));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "", "", "", "", "");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public void Integration_InsertUsuarioDadosErrados()
        {
            var domain = new USUARIO(Guid.NewGuid(), "USU4RI0 T3ST3", new CPF("19865260abc"), new RG(DATA.Enum.EnumUF.PE, "abc1589"), DateTime.Now.AddYears(-50));
            domain.AdicionarContato(new Telefone("3379tyui"), new Telefone("994059sed"), new Email("umteste3@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "5074zxsc", "Rua Azeredo Coutinho", "", "Várzea", "Recife");

            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }
    }
}