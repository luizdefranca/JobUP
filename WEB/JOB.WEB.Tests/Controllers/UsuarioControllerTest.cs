using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.DATA.ValueObject;
using JOB.WEB.Controllers;
using JOB.WEB.Models;
using NUnit.Framework;
using System;
using System.Data.Entity;
using System.Threading.Tasks;
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
        public async Task Integration_ValidarInsertUsuario()
        {
            //gera uma nova classe para testes
            var domain = new USUARIO(new Guid(), "USUARIO TESTE", new CPF("50869388720"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste1@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            //se comunica com o controller
            var ctx = new Contexto();
            ctx.Database.BeginTransaction(); //inicia uma transação para controlar os dados manipulados no banco de dados

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            //recupera o novo usuario inserido no banco
            var domainNew = await ctx.Usuario.FirstAsync(w => w.CPF.NR == "50869388720");

            ctx.Database.CurrentTransaction.Rollback(); //retorna qualquer mudança feita no banco de dados

            //teste finalmente se existe usuario inserido no banco e é o mesmo gerado no inicio do método
            Assert.AreEqual(model.CPF, domainNew.CPF.NR);
        }

        [Test]
        public async Task Integration_InsertUsuarioNomeFaltando()
        {
            var domain = new USUARIO(new Guid(), "", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone(""), new Email("umteste2@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioCPFFaltando()
        {
            var domain = new USUARIO(new Guid(), "USUARIO DE TESTE", new CPF(""), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste3@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioRGFaltando()
        {
            var domain = new USUARIO(new Guid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste4@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioTelefoneFaltando()
        {
            var domain = new USUARIO(new Guid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone(""), new Telefone("994059945"), new Email("umteste5@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioCelularFaltando()
        {
            var domain = new USUARIO(new Guid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone(""), new Email("umteste6@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioEmailFaltando()
        {
            var domain = new USUARIO(new Guid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email(""));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioCEPFaltando()
        {
            var domain = new USUARIO(new Guid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste7@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "", "Rua Azeredo Coutinho", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioRuaFaltando()
        {
            var domain = new USUARIO(new Guid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste8@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "", "", "Várzea", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioBairroFaltando()
        {
            var domain = new USUARIO(new Guid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste2@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Bairro", "Recife");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioCidadeFaltando()
        {
            var domain = new USUARIO(new Guid(), "USUARIO DE TESTE", new CPF("19854269476"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            domain.AdicionarContato(new Telefone("33793968"), new Telefone("994059945"), new Email("umteste2@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "50741970", "Rua Azeredo Coutinho", "", "Várzea", "");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioTodosDadosFaltando()
        {
            var domain = new USUARIO(new Guid(), "", new CPF(""), new RG(DATA.Enum.EnumUF.PE, ""), DateTime.Now.AddYears(-0));
            domain.AdicionarContato(new Telefone(""), new Telefone(""), new Email(""));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "", "", "", "", "");
            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }

        [Test]
        public async Task Integration_InsertUsuarioDadosErrados()
        {
            var domain = new USUARIO(new Guid(), "USU4RI0 T3ST3", new CPF("19865260abc"), new RG(DATA.Enum.EnumUF.PE, "abc1589"), DateTime.Now.AddYears(-50));
            domain.AdicionarContato(new Telefone("3379tyui"), new Telefone("994059sed"), new Email("umteste3@gmail.com"));
            domain.AdicionarEndereco(DATA.Enum.EnumUF.PE, "5074zxsc", "Rua Azeredo Coutinho", "", "Várzea", "Recife");

            var model = Mapper.Map<UsuarioViewModel>(domain);

            ctx.Database.BeginTransaction();

            var controller = new ManageController(ctx);
            ViewResult result = await controller.Create(model) as ViewResult;

            ctx.Database.CurrentTransaction.Rollback();
        }
    }
}