from trajGRU import *


def train(*args):
    # use mse + mae loss ..
    model, epochs, LR, dataset = args
    criterion = nn.MSELoss()

    l1 = nn.L1Loss()
    optimizer = torch.optim.Adam(model.parameters(), lr=LR)
    mult_step_scheduler = lr_scheduler.MultiStepLR(optimizer, milestones=[30, 60], gamma=0.1)
    # resume learning......
    # model.load_state_dict(torch.load('MSEModels/ckpt-maemse-80-38.089400.pth'))
    model.train()
    model.cuda()

    for i in range(0, epochs):
        # print(f'=====training epoch {i+1}======')
        logger.info(f'=====training epoch {i + 1}======')
        epoch_start = time.time()
        train_loss = 0
        prefetcher = data_prefetcher(dataset)
        x, y = prefetcher.next()
        iteration = 0
        while x is not None:
            iteration += 1
            x = x.cuda()
            y = y.cuda()
            optimizer.zero_grad()
            outputs = model(x)
            # loss = criterion(outputs,y) + l1(outputs,y)
            loss = criterion(outputs, y)
            iterationLoss = loss.item()
            train_loss += iterationLoss
            loss.backward()
            optimizer.step()
            # if iteration % 20 == 0:
            # logger.info(f'epoch {i+1} index {iteration} mse_mae_loss {iterationLoss}')
            logger.info(f'epoch {i + 1} index {iteration} mse_loss {iterationLoss}')
            x, y = prefetcher.next()

        mult_step_scheduler.step()
        epoch_end = time.time()
        # logger.info(f'epoch {i+1} final mse_mae_loss {round(train_loss/iteration,3)} epoch uses {(epoch_end-epoch_start)//60}minutes')
        logger.info(
            f'epoch {i + 1} final mse {round(train_loss / iteration, 3)} epoch uses {(epoch_end - epoch_start) // 60}minutes')
        torch.save(model.state_dict(), 'checkPoints/ckpt-mse-%d-%f.pth' % (i + 1, round(train_loss / iteration, 3)))
        # torch.save(model.module.state_dict(),'ckpt-%d-%f.pth'%(i+1,round(train_loss/500,4)))
