package compasses.expandedstorage.registry.content;

final class BaseContentModule {
    record Result() {
    }

    private BaseContentModule() {
    }

    static Result create(ContentContext context) {
        return new Result();
    }
}

